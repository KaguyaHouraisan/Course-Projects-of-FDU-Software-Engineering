# coding=utf-8
from torch import nn, optim
import torch


def setup_seed(seed):
    torch.manual_seed(seed)
    torch.cuda.manual_seed_all(seed)
    torch.backends.cudnn.deterministic = True
    torch.backends.cudnn.benchmark = True


def accuracy_count(x, label):
    _, predicted = torch.max(x, 1)
    return (predicted == label).cpu().sum()


class Model(nn.Module):
    def __init__(self):
        super(Model, self).__init__()
        # 卷积层、池化层和激活函数层
        self.conv = nn.Sequential(nn.Conv2d(3, 128, (3, 3), 1, 1),  # 输入通道数为3，输出为64，卷积核为3*3，步长为1，padding为1
                                  nn.ReLU(inplace=True),  # 激活函数
                                  nn.MaxPool2d(2),  # 2*2最大池化
                                  nn.Conv2d(128, 256, (3, 3), 1, 1),
                                  nn.ReLU(inplace=True),
                                  nn.MaxPool2d(2))

        # 全连接层
        self.fc = nn.Sequential(nn.Flatten(),
                                nn.Linear(7 * 7 * 256, 256),
                                nn.Dropout(inplace=True),
                                nn.ReLU(inplace=True),
                                nn.Linear(256, 12))

    def forward(self, x):
        return self.fc(self.conv(x))
