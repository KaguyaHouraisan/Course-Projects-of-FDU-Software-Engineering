import numpy as np
import HMM
import math
import pickle

if_train = False

if if_train:
    parm_num = 3
    x_num = 10
    hmm = HMM.DiscreteHMM(parm_num, x_num, 100)

    X = []
    with open(r'../data/observation.utf8', 'r', encoding='utf-8') as file:
        content_list = file.readlines()
    result = [x.strip() for x in content_list]
    for x in result:
        temp = []
        for item in x:
            temp.append([int(item)])
        X.append(temp)
    X = np.array(X)

    hmm.train_batch(X)
    model_save = open('model', 'wb')
    pickle.dump(hmm, model_save)
    model_save.close()

else:
    model_save = open('model', 'rb')
    hmm = pickle.load(model_save)
    model_save.close()

    result_key = []
    result_value = []
    for x in range(10):
        temp = str(x).zfill(7)
        str_num = []
        for item in temp:
            str_num.append([int(item)])
        result_key.append(temp)
        result_value.append(math.exp(hmm.X_prob(str_num)))

    for x in range(10, 10000000):
        temp = str(x).zfill(7)
        str_num = []
        for item in temp:
            str_num.append([int(item)])
        hmm_prob = math.exp(hmm.X_prob(str_num))

        if min(result_value) < hmm_prob:
            min_index = result_value.index(min(result_value))
            result_key[min_index] = temp
            result_value[min_index] = hmm_prob

        print(temp + ": " + str(hmm_prob))

    for x in range(10):
        print("Result: " + result_key[x] + ": " + str(result_value[x]))

    with open('handin.utf8', 'w') as file_object:
        for x in range(10):
            file_object.write(str(result_key[x]) + "\n")
