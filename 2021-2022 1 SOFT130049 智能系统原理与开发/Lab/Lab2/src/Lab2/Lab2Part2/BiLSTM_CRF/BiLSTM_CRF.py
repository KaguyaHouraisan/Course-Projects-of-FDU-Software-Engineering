import torch
import torch.nn as nn
import torch.optim as optim

START_TAG, END_TAG = "<s>", "<e>"


def log_sum_exp(smat):
    """
    :param: smat 是 "status matrix", DP状态矩阵; 其中 smat[i][j] 表示 上一帧为i状态且当前帧为j状态的分值
    作用：针对输入的【二维数组的每一列】, 各元素分别取exp之后求和再取log
    """

    vmax = smat.max(dim=0, keepdim=True).values  # 每一列最大数
    return (smat - vmax).exp().sum(axis=0, keepdim=True).log() + vmax


class BiLSTM_CRF(nn.Module):
    def __init__(self, tag2ix, word2ix, embedding_dim, hidden_dim):
        """
        :param tag2ix: 标签 -> 下标的映射
        :param word2ix: 单词 -> 下标的映射
        :param embedding_dim: 输入词向量的维度
        :param hidden_dim: 期望的输出层维度
        """

        super(BiLSTM_CRF, self).__init__()
        assert hidden_dim % 2 == 0, 'hidden_dim must be even for Bi-LSTM'
        self.embedding_dim, self.hidden_dim = embedding_dim, hidden_dim
        self.tag2ix, self.word2ix, self.n_tags = tag2ix, word2ix, len(tag2ix)
        self.word_embeds = nn.Embedding(len(word2ix) + 1, embedding_dim)  # 词嵌入
        self.lstm = nn.LSTM(embedding_dim, hidden_dim // 2, num_layers=1, bidirectional=True)
        self.hidden2tag = nn.Linear(hidden_dim, self.n_tags)  # 将LSTM输出摊到标签空间
        self.transitions = nn.Parameter(torch.randn(self.n_tags, self.n_tags))  # CRF转移矩阵
        # START_TAG之前，END_TAG之后无意义
        self.transitions.data[:, tag2ix[START_TAG]] = self.transitions.data[tag2ix[END_TAG], :] = -10000

    def neg_log_likelihood(self, words, tags):  # loss function
        frames = self._get_lstm_features(words)  # 每帧对应到每种tag的发射矩阵
        gold_score = self._score_sentence(frames, tags)  # 正确路径的分数
        forward_score = self._forward_alg(frames)  # 所有路径分数之和
        return -(gold_score - forward_score)

    def _get_lstm_features(self, words):  # LSTM每一帧的隐向量
        embeds = self.word_embeds(self._to_tensor(words, self.word2ix)).view(len(words), 1, -1)
        hidden = torch.randn(2, 1, self.hidden_dim // 2), torch.randn(2, 1, self.hidden_dim // 2)  # LSTM隐状态初始化
        lstm_out, _hidden = self.lstm(embeds, hidden)
        return self.hidden2tag(lstm_out.squeeze(1))  # 去掉batch，摊到标签空间

    def _score_sentence(self, frames, tags):  # CRF score: frames -> tags
        tags_tensor = self._to_tensor([START_TAG] + tags, self.tag2ix)
        score = torch.zeros(1)
        for i, frame in enumerate(frames):  # score += 转移 + 发射
            score += self.transitions[tags_tensor[i], tags_tensor[i + 1]] + frame[tags_tensor[i + 1]]
        return score + self.transitions[tags_tensor[-1], self.tag2ix[END_TAG]]  # 加END_TAG转移

    def _forward_alg(self, frames):  # CRF概率归一化，原理上共有tag_num^N条路径，复杂度太高，故采用log_sum_exp
        # 给定每一帧的发射分值，计算所有可能序列的分值和，用作CRF中概率归一化分母
        alpha = torch.full((1, self.n_tags), -10000.0)
        alpha[0][self.tag2ix[START_TAG]] = 0  # 初始化分值分布
        for frame in frames:  # 当前各状态分值+发射分值+转移矩阵 => 当前帧到达每个状态的分值（综合所有来源）
            # 对于到词 w(i+1) 的路径，可以先把到词 w(i) 的logsumexp计算出来
            alpha = log_sum_exp(alpha.T + frame.unsqueeze(0) + self.transitions)
        return log_sum_exp(alpha.T + 0 + self.transitions[:, [self.tag2ix[END_TAG]]]).flatten()

    def _viterbi_decode(self, feats):
        backpointers = []
        init_vvars = torch.full((1, self.n_tags), -10000.)
        init_vvars[0][self.tag2ix[START_TAG]] = 0
        forward_var = init_vvars
        for feat in feats:
            bptrs_t = []
            viterbivars_t = []
            for next_tag in range(self.n_tags):
                next_tag_var = forward_var + self.transitions[next_tag]
                _, idx = torch.max(next_tag_var, 1)
                best_tag_id = idx.item()
                bptrs_t.append(best_tag_id)
                viterbivars_t.append(next_tag_var[0][best_tag_id].view(1))
            forward_var = (torch.cat(viterbivars_t) + feat).view(1, -1)
            backpointers.append(bptrs_t)
        terminal_var = forward_var + self.transitions[self.tag2ix[END_TAG]]
        _, idx = torch.max(terminal_var, 1)
        best_tag_id = idx.item()
        path_score = terminal_var[0][best_tag_id]

        best_path = [best_tag_id]
        for bptrs_t in reversed(backpointers):
            best_tag_id = bptrs_t[best_tag_id]
            best_path.append(best_tag_id)

        start = best_path.pop()
        assert start == self.tag2ix[START_TAG]
        best_path.reverse()
        result = []
        for i in best_path:
            if i == 0:
                result.append('B')
            elif i == 1:
                result.append('I')
            elif i == 2:
                result.append('E')
            elif i == 3:
                result.append('S')
            else:
                result.append('N')
        result_path = ''.join(result)
        return result_path

    def forward(self, words):
        # print(self.word2ix)
        lstm_feats = self._get_lstm_features(words)  # 求出每一帧的发射矩阵
        return self._viterbi_decode(lstm_feats)

    def _to_tensor(self, words, to_ix):  # 将words/tags序列数值化，即映射为相应下标序列张量
        result = []
        for w in words:
            if w not in to_ix.keys():
                result.append(to_ix['U'])
            else:
                result.append(to_ix[w])
        return torch.tensor(result, dtype=torch.long)


def read_data(file):
    sentences = []
    labels = []
    with open(file, encoding='utf-8') as f:
        sentence_buf = []
        labels_buf = []
        for line in f:
            line = line.strip()
            if not line:
                sentences.append(sentence_buf)
                labels.append(labels_buf)
                sentence_buf = []
                labels_buf = []
                continue

            data = line.split(" ")
            sentence_buf.append(data[0])
            labels_buf.append(data[1])
    return [sentences, labels]


is_train = False
if is_train:
    data = read_data('../data/train_dataset/dataset/train.utf8')
    sentences = data[0]
    labels = data[1]
    training_data = []
    for i in range(len(sentences)):
        training_data.append((sentences[i], labels[i]))

    if_model = True
    if if_model:
        model = torch.load('../data/model_dataset/lstm_model.pkl')
        model.eval()
    else:
        model = BiLSTM_CRF(tag2ix={"B": 0, "I": 1, "E": 2, "S": 3, START_TAG: 4, END_TAG: 5},
                           word2ix={w: i for i, w in enumerate({w for s, _ in training_data for w in s})},
                           embedding_dim=5, hidden_dim=4)

    optimizer = optim.SGD(model.parameters(), lr=0.01, weight_decay=1e-4)
    for epoch in range(100):
        count = 0
        for words, tags in training_data:
            count += 1
            print(count)
            model.zero_grad()  # 更改 torch 默认的累积梯度为每条样本单独计算梯度
            if len(words) == 0:
                continue
            loss = model.neg_log_likelihood(words, tags)
            loss.backward()  # 回传梯度
            optimizer.step()  # 梯度下降，更新参数
        print('epoch ' + str(epoch) + ' loss: ' + str(loss.data[0]))
        torch.save(model, '../data/model_dataset/lstm_model.pkl')
        torch.save(model.state_dict(), '../data/model_dataset/lstm_model_all.pkl')
