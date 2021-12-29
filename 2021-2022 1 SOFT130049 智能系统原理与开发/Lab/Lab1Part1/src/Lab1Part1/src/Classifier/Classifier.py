# coding=utf-8
import numpy as np
import pickle
from tqdm import tqdm
from DataLoader import *
from GradientDescent import *


def accuracy(y_test_hat, y_test, nums, flag=False):
    test_hat = (y_test_hat == np.max(y_test_hat, axis=1).reshape(y_test.shape[0], 1)).astype(int)
    labels = np.array(list(range(nums))).reshape(nums, 1)
    th = np.matmul(test_hat, labels)
    if flag:
        tmp = np.matmul(y_test, labels)
        return np.sum(th == tmp)
    else:
        return np.sum(th == y_test.reshape(y_test.shape[0], 1))


def train(train_model, epoch_num, lr, train_path, test_path):
    criterion = CrossEntropyLoss(nnModel=train_model)
    optimizer = GradientDescent(train_model, lr=lr)
    costs = []
    train_data = TrainData(train_path)
    test_data = TestData(test_path)
    m_train = train_data.__len__()
    m_test = test_data.__len__()
    train_loader = DataLoader(train_data, batch_size=32)
    test_loader = DataLoader(test_data, batch_size=128)
    train_accuracy = []
    test_accuracy = []
    for epoch in range(epoch_num):
        tn = 0
        for x_train, y_train in tqdm(train_loader):
            y_hat = train_model(x_train)
            loss = criterion(y_hat, y_train)
            costs.append(loss.item)
            tn += accuracy(y_test_hat=y_hat, y_test=y_train, nums=12, flag=True)
            loss.backward()
            optimizer.step()
        train_accuracy.append(tn / m_train)
        tn = 0
        for xv, yv in test_loader:
            yv_hat = train_model(xv, eval_pattern=True)
            tn += accuracy(yv_hat, yv, 12)
        test_accuracy.append(tn / m_test)

        print('Round:{}\000\000Train accuracy:{:.2%}\000\000Test accuracy:{:.2%}'.format(epoch + 1, train_accuracy[-1],
                                                                                         test_accuracy[-1]))


if __name__ == '__main__':
    model = Sequential([Flatten(), Linear(inputs_dim=3136, outputs_dim=512), Relu(),
                        Linear(inputs_dim=512, outputs_dim=128), Relu(), Linear(inputs_dim=128, outputs_dim=12)])
    train(train_model=model, epoch_num=100, lr=5e-2,
          train_path="D://ProgramsTest//Python//ISPP//Lab1Part1//src//Classifier//train_data//train",
          test_path="D://ProgramsTest//Python//ISPP//Lab1Part1//src//Classifier//train_data//test")

    model_save = open('D://ProgramsTest//Python//ISPP//Lab1Part1//src//Classifier//model', 'wb')
    pickle.dump(model, model_save)
