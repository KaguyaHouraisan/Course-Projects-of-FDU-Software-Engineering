import pickle


class CRF(object):
    def __init__(self, template_file):

        self.score_map = {}
        self.unigram = [[]]
        self.bigram = [[]]
        self.read_template(template_file)
        self.num_status = {
            0: 'B',
            1: 'I',
            2: 'E',
            3: 'S',
        }
        self.status_num = {
            'B': 0,
            'I': 1,
            'E': 2,
            'S': 3,
        }

        # 存取结果
        self.model_file = '../data/model_dataset/crf_model.pkl'

        # 是否重新加载model_file
        self.load_para = False

    def try_load_model(self, trained):
        # 加载结果
        if trained:
            import pickle
            with open(self.model_file, 'rb') as f:
                self.score_map = pickle.load(f)
                self.load_para = True

        else:
            # self.score_map = {}
            self.load_para = False

    def read_template(self, file):
        with open(file, encoding='utf8') as f:
            for line in f:
                line = line.strip()
                if not line: continue
                if line[0] == '#':
                    continue
                rows = []
                index = 0
                index2 = 0
                while index != -1:
                    try:
                        index = line.index('[', index + 1)
                        index2 = line.index(',', index2 + 1)
                        t = line[index + 1:index2]
                        if index != -1:
                            rows.append(int(t))
                    except ValueError:
                        index = -1
                if len(rows) > 0:
                    if line[0] == 'U':
                        self.unigram.append(rows)
                    elif line[0] == 'B':
                        self.bigram.append(rows)

    def make_score_key(self, template, t_id, sentence, pos, status):
        key = str(t_id)
        for offset in template:
            index = pos + offset
            if index < 0 or index >= len(sentence):
                key += " "
            else:
                key += sentence[index]
        key += '/'
        key += status
        return key

    def get_u_score(self, sentence, pos, status):
        u_score = 0
        for i in range(len(self.unigram)):
            key = self.make_score_key(self.unigram[i], i, sentence, pos, status)
            if self.score_map.get(key) is not None:
                u_score += self.score_map.get(key)
        return u_score

    def get_b_score(self, sentence, pos, pre_status, this_status):
        b_score = 0
        for i in range(len(self.bigram)):
            key = self.make_score_key(self.bigram[i], i, sentence, pos, pre_status + this_status)
            if self.score_map.get(key) is not None:
                b_score += self.score_map.get(key)
        return b_score

    def segment(self, sentence):
        if len(sentence) == 0:
            return []

        status_from = [[0 for i in range(len(sentence))] for i in range(4)]
        max_score = [[0 for i in range(len(sentence))] for i in range(4)]

        for col in range(len(sentence)):
            for row in range(4):
                status = self.num_status.get(row)
                if col == 0:  # first word
                    u_score = self.get_u_score(sentence, 0, status)
                    b_score = self.get_b_score(sentence, 0, " ", status)
                    max_score[row][0] = u_score + b_score
                    status_from[row][0] = None
                else:
                    scores = []
                    for i in range(4):
                        pre_status = self.num_status.get(i)
                        tran_score = max_score[i][col - 1]
                        u_score = self.get_u_score(sentence, col, status)
                        b_score = self.get_b_score(sentence, col, pre_status, status)
                        scores.append(tran_score + u_score + b_score)
                    max_index = scores.index(max(scores))
                    max_score[row][col] = scores[max_index]
                    status_from[row][col] = self.num_status.get(max_index)

        result = [0 for i in range(len(sentence))]
        last_scores = [0 for i in range(4)]
        for i in range(4):
            last_scores[i] = max_score[i][-1]
        result[-1] = self.num_status.get(last_scores.index(max(last_scores)))

        for i in range(len(sentence) - 2, -1, -1):
            result[i] = status_from[self.status_num.get(result[i + 1])][i + 1]

        return ''.join(result)

    def train(self, sentence, gold_result):
        self.try_load_model(False)
        result = self.segment(sentence)
        wrong_num = 0
        for i in range(len(sentence)):
            word_result = result[i]
            gold_word_result = gold_result[i]
            if word_result != gold_word_result:
                wrong_num += 1
                # update unigram
                for u in range(len(self.unigram)):
                    # punishment
                    key = self.make_score_key(self.unigram[u], u, sentence, i, word_result)
                    if self.score_map.get(key) is None:
                        self.score_map[key] = -1
                    else:
                        old_score = self.score_map.get(key)
                        self.score_map[key] = old_score - 1

                    # encouragement
                    gold_key = self.make_score_key(self.unigram[u], u, sentence, i, gold_word_result)
                    if self.score_map.get(gold_key) is None:
                        self.score_map[gold_key] = 1
                    else:
                        old_gold_score = self.score_map.get(gold_key)
                        self.score_map[gold_key] = old_gold_score + 1
                # update bigram
                for b in range(len(self.bigram)):
                    if i >= 1:
                        key = self.make_score_key(self.bigram[b], b, sentence, i, result[i - 1:i])
                        gold_key = self.make_score_key(self.bigram[b], b, sentence, i, ''.join(gold_result[i - 1:i]))
                    else:
                        key = self.make_score_key(self.bigram[b], b, sentence, i, " " + word_result)
                        gold_key = self.make_score_key(self.bigram[b], b, sentence, i, gold_word_result)

                    # punishment
                    if self.score_map.get(key) is None:
                        self.score_map[key] = -1
                    else:
                        old_score = self.score_map.get(key)
                        self.score_map[key] = old_score - 1

                    # encouragement
                    if self.score_map.get(gold_key) is None:
                        self.score_map[gold_key] = 1
                    else:
                        old_gold_score = self.score_map.get(gold_key)
                        self.score_map[gold_key] = old_gold_score + 1

        return wrong_num


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


def get_same_num(s1, s2):
    count = 0
    for i in range(len(s1)):
        if s1[i] == s2[i]:
            count += 1
    return count


# 是否进行训练
if_train = False

if if_train:
    crf = CRF('../data/train_dataset/dataset/template.utf8')
    data = read_data('../data/train_dataset/dataset/train.utf8')
    sentences = data[0]
    labels = data[1]

    # train
    for i in range(10):
        wrong_num = 0
        total = 0
        for j in range(28950):
            sentence = sentences[j]
            label = labels[j]
            total += len(sentence)
            wrong_num += crf.train(sentence, label)
            print(j)
        correct_num = total - wrong_num
        print('epoch ' + str(i) + ' accuracy: ' + str(correct_num / total))

    # 序列化
    with open(crf.model_file, 'wb') as f:
        pickle.dump(crf.score_map, f)

    # test
    total = 0
    correct = 0
    for i in range(28950, 29000):
        sentence = sentences[i]
        total += len(sentence)
        label = labels[i]
        result = crf.segment(sentence)
        correct += get_same_num(label, result)
    print('accuracy in test: ' + str(correct / total))
