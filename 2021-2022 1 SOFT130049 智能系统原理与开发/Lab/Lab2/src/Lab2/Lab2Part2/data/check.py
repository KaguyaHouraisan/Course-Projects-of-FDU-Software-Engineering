from wordseg.solution import Solution
from Lab2Part2.BiLSTM_CRF.BiLSTM_CRF import BiLSTM_CRF

solution = Solution()

funcs = {
    "CRF": solution.crf_predict,
    "BiLSTM-CRF": solution.dnn_predict
}

inputs = ["我爱北京天安门", "今天天气怎么样", "中华人民共和国"]

for f_name, func in funcs.items():
    outputs = func(inputs)
    print(f"对于输入{inputs}，你的输出是{outputs}。")


for f_name, func in funcs.items():
    print(f"\n{f_name}模型 -->")
    examples = open("test_dataset/input.utf8", encoding="utf8").readlines()
    examples = [ele.strip() for ele in examples]
    gold = open("test_dataset/gold.utf8", encoding="utf8").readlines()
    gold = [ele.strip() for ele in gold]
    pred = func(examples)  # 你预测的结果。
    accuracys = []
    if pred is not None:
        for i in range(len(examples)):
            corr = [1 if a == b else 0 for a, b in zip(str(gold[i]), str(pred[i]))]
            accu = sum(corr) / len(corr)
            accuracys.append(accu)
        print(f"准确率：{sum(accuracys) / len(accuracys)}")
    else:
        print(f"{f_name}暂无结果")
