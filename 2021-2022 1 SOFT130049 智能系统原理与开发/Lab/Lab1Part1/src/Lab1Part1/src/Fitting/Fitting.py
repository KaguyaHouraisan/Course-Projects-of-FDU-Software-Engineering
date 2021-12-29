# coding=utf-8
import numpy as np
from matplotlib import pyplot as plt


# 激活函数：f(σ) = 1 / (1 + e^–σ)
def sigmoid(z):
    return 1.0 / (1.0 + np.exp(-z))


# 激活函数的导数：f'(σ) = f(σ)(1 – f(σ))
def d_sigmoid(z):
    return sigmoid(z) * (1 - sigmoid(z))


# 计算平均误差：损失函数（平方误差）：Error = (∑((ti - y(xi)) ^ 2)) / 2
def compute_loss(predict, label):
    return np.mean(1 / 2 * (label - predict) ** 2)


# 加载数据集
def dataloader():
    para_x = np.arange(0.0, 1.0, 0.01)
    para_y = 20 * np.sin(2 * np.pi * para_x)
    return para_x, para_y


class BPNetwork:
    # BP神经网络：layers为每层神经元的个数，r为学习率
    def __init__(self, layers, r):
        self.parameter = {}     # 保存各层的权重和偏差
        self.d_parameter = {}   # 保存各层的权重和偏差的梯度
        self.r = r              # 保存学习率
        # 初始化权重矩阵
        for num in range(1, len(layers)):
            self.parameter["w" + str(num)] = np.random.random([layers[num], layers[num - 1]])
            self.parameter["b" + str(num)] = np.zeros([layers[num], 1])
        self.L = len(self.parameter) // 2

    # 前向传播
    def forward(self, para_x):
        a = []            # 保存每一层经过激活函数的输出值
        z = []            # 保存每一层未经过激活函数的输出值
        a.append(para_x)  # 为便于计算，将输入层和输出层都用a,z保存
        z.append(para_x)
        # 第一层为输入层，而最后一层是输出层
        for num in range(1, self.L):
            _z = self.parameter["w" + str(num)].dot(a[num - 1]) + self.parameter['b' + str(num)]
            _a = sigmoid(_z)  # 激活函数
            z.append(_z)
            a.append(_a)
        # 输出层不需要经过激活函数
        _z = self.parameter["w" + str(self.L)].dot(a[self.L - 1]) + self.parameter['b' + str(self.L)]
        # 为了保持维度一致
        _a = _z  # 将输出层的值也添加到a,z中，保持a,z含义一致：每一层激活后和未激活后输出值
        z.append(_z)
        a.append(_a)
        temp = {"z": z, "a": a}  # 保存每一层激活和未激活的输出值
        return temp, a[self.L]

    # 定义反向传播求梯度
    def backward(self, temp, predict, label):
        # 先求输出层到上一层的倒数
        m = label.shape[1]
        self.d_parameter["dz" + str(self.L)] = predict - label
        # 最后一层没有激活函数
        self.d_parameter["dw" + str(self.L)] = self.d_parameter["dz" + str(self.L)].dot(temp['a'][self.L - 1].T) / m
        self.d_parameter["db" + str(self.L)] = np.sum(self.d_parameter["dz" + str(self.L)], axis=1, keepdims=True) / m
        for num in reversed(range(1, self.L)):
            self.d_parameter["dz" + str(num)] = self.parameter["w" + str(num + 1)].T.dot(
                self.d_parameter["dz" + str(num + 1)]) * d_sigmoid(temp['z'][num])
            self.d_parameter["dw" + str(num)] = self.d_parameter["dz" + str(num)].dot(temp['a'][num - 1].T) / m
            self.d_parameter["db" + str(num)] = np.sum(self.d_parameter["dz" + str(num)], axis=1, keepdims=True) / m

    # 权重更新
    def update_para(self):
        for num in range(1, self.L + 1):
            self.parameter["w" + str(num)] -= self.r * self.d_parameter["dw" + str(num)]
            self.parameter["b" + str(num)] -= self.r * self.d_parameter["db" + str(num)]


# 加载数据集
x, y = dataloader()
x = x.reshape(1, 100)
y = y.reshape(1, 100)

# 迭代次数设为20000
cycles = 20000

# 设置参数，共3层，其中中间层神经元个数设为25，学习率设为0.1（可调节）
net = BPNetwork((1, 25, 1), 0.1)

# 训练网络参数
output = None
for i in range(cycles):
    cache, output = net.forward(x)
    net.backward(cache, output, y)
    net.update_para()
    loss = compute_loss(output, y)
    print("[%i] The loss is %f" % (i, loss))

# 画图
plt.title("y=sin(x)")
plt.xlabel("x")
plt.ylabel("y")
x = x.flatten()
y = y.flatten()
output = output.flatten()
plt.plot(x, output, label='Predict Output')
plt.plot(x, y, label='Ground Truth')
plt.legend()
plt.show()
