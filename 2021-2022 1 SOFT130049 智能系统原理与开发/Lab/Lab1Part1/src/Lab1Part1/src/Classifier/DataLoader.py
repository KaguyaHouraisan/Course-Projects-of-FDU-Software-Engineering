# coding=utf-8
import numpy as np
import os
import matplotlib.pyplot as plt


def dataloader(pth):
    x = []
    y = []
    for label in range(1, 13):
        path = os.path.join(pth, str(label))
        filenames = os.listdir(path)
        for img in filenames:
            x.append(plt.imread(os.path.join(path, img)))
            y.append(label)
    return np.array(x) / 255, np.array(y) - 1


class Dataset:
    def __init__(self):
        pass

    def __len__(self):
        raise NotImplementedError

    def __getitem__(self, index):
        raise NotImplementedError


class TrainData(Dataset):
    def __init__(self, pth):
        Dataset.__init__(self)
        self.x, self.y = dataloader(pth)
        self.y = np.eye(12)[self.y]

    def __len__(self):
        return self.y.shape[0]

    def __getitem__(self, index):
        x = self.x[index]
        return x, self.y[index]


class TestData(Dataset):
    def __init__(self, pth):
        Dataset.__init__(self)
        self.x, self.y = dataloader(pth)

    def __len__(self):
        return self.y.shape[0]

    def __getitem__(self, index):
        return self.x[index], self.y[index]


class DataLoader:
    def __init__(self, dataset, batch_size, shuffle=True, drop_last=False):
        self.dataset = dataset
        # 每一次训练选取的样本数，提高到一定程度可以增加梯度的准确性
        self.batchSize = batch_size
        self.shuffle = shuffle
        self.drop_last = drop_last
        self.__reset()

    def __reset(self):
        m = self.dataset.__len__()
        index_list = list(range(m))
        if self.shuffle:
            np.random.shuffle(index_list)
        self.index_iter = []
        if self.drop_last:
            index_list = index_list[:(m // self.batchSize * self.batchSize)]
        for i in range(0, m, self.batchSize):
            self.index_iter.append(index_list[i:(i + self.batchSize)])
        self.index_iter = list(reversed(self.index_iter))

    def __next__(self):
        if not self.index_iter:
            self.__reset()
            raise StopIteration
        else:
            index = self.index_iter.pop()
            return self.dataset.__getitem__(index)

    def __iter__(self):
        return self
