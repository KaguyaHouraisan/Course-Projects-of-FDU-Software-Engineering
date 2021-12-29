# coding=utf-8
import numpy as np


# 随机梯度下降算法
class GradientDescent:
    def __init__(self, sequential, lr, momentum=0.9, weight_decay=0, lr_decay=1):
        self.sequential = sequential
        self.lr = lr
        self.momentum = momentum
        self.weight_decay = weight_decay
        self.v_dict = {}
        self.count = 0
        self.lr_decay = lr_decay
        for name, layer in self.sequential.layers.items():
            try:
                params = layer.parameters
                self.v_dict[name] = {}
                for param in params.keys():
                    self.v_dict[name][param] = 0
            except:
                continue

    def step(self):
        lr = self.lr * (self.lr_decay ** self.count)
        self.count += 1
        for layer_name, layer in self.sequential.layers.items():
            try:
                for param in layer.parameters.keys():
                    self.v_dict[layer_name][param] = self.momentum * self.v_dict[layer_name][param] + (
                            1 - self.momentum) * layer.grads[param]
                    v_corrected = self.v_dict[layer_name][param] / (1 - self.momentum ** self.count)
                    layer.parameters[param] = (1 - self.weight_decay) * layer.parameters[param] - self.lr * v_corrected
            except:
                continue


class Modules:
    def __init__(self):
        raise NotImplementedError

    def forward(self, x, eval_pattern):
        for layer in self.layers.values():
            if eval_pattern and layer.__class__.__name__ == 'Droupout':
                continue
            x = layer(x)
        return x

    def backward(self, dl):
        for name, layer in reversed(self.layers.items()):
            # 如果是第一层就不再反向传播
            if name == 'conv2d1':
                dl = layer.backward(dl, backward=False)
            else:
                dl = layer.backward(dl)

    def __call__(self, x, eval_pattern=False):
        return self.forward(x, eval_pattern=eval_pattern)

    def state_dict(self):
        return self.parameters

    def save_state_dict(self, path):
        import pickle
        with open(path, 'wb') as f:
            pickle.dump(self.parameters, f, pickle.HIGHEST_PROTOCOL)
        return

    def load_state_dict(self, state_dict=None, path=None):
        if not state_dict:
            import pickle
            with open(path, 'rb') as f:
                state_dict = pickle.load(f)
        for layer in state_dict.keys():
            for param, v in state_dict[layer].items():
                self.parameters[layer][param] = v
        return


class Sequential(Modules):
    def __init__(self, architecture):
        from collections import OrderedDict
        if not isinstance(architecture, OrderedDict):
            cdict = {}
            self.layers = OrderedDict()
            self.parameters = OrderedDict()
            for layer in architecture:
                name = layer.__class__.__name__
                try:
                    count = cdict[name] + 1
                except:
                    count = 1
                cdict[name] = count
                self.layers[name.lower() + str(count)] = layer
                try:
                    self.parameters[name.lower() + str(count)] = layer.parameters
                except:
                    pass
        else:
            self.layers = architecture


class Loss:
    def __init__(self, loss, grad, nnModel):
        # 模型nn初始化
        self.nn = nnModel
        self.item = loss
        self.grad = grad

    def backward(self):
        self.nn.backward(self.grad)


# 初始类
class Layer:

    def forward(self, x):
        raise NotImplementedError

    def backward(self, dz):
        raise NotImplementedError

    def __call__(self, x):
        return self.forward(x)


# 在一个层内进行线性变换
class Linear(Layer):
    def __init__(self, inputs_dim, outputs_dim, bias=True):
        self.parameters = {}
        self.grads = {}
        self.parameters['w'] = np.random.randn(inputs_dim, outputs_dim) * 0.01
        if bias:
            self.parameters['b'] = np.zeros((1, outputs_dim))
        self.bias = bias

    # 前向传播
    def forward(self, x):
        # Z=WX+B
        self.cache = [x]
        if self.bias:
            return np.matmul(x, self.parameters['w']) + self.parameters['b']
        else:
            return np.matmul(x, self.parameters['w'])

    # 反向传播
    def backward(self, dz):
        x = self.cache[0]
        self.grads['w'] = np.matmul(x.T, dz) / x.shape[0]
        if self.bias:
            self.grads['b'] = np.sum(dz, axis=0) / x.shape[0]
        dx = np.matmul(dz, self.parameters['w'].T)
        return dx


class Flatten(Layer):
    def __init__(self):
        pass

    def forward(self, inputs):
        self.shape = inputs.shape
        a = inputs.reshape((self.shape[0], -1))
        return a

    def backward(self, da):
        return da.reshape(self.shape)


class Relu(Layer):
    def __init__(self):
        pass

    def forward(self, z):
        self.cache = [z]
        return (z + abs(z)) / 2

    def backward(self, da):
        z = self.cache[0]
        dz = da * (z > 0).astype(int)
        return dz


# 激活函数
class Sigmoid(Layer):
    def __init__(self):
        pass

    def forward(self, z):
        a = np.exp(z) / (1 + np.exp(z))
        self.cache = [a]
        return a

    def backward(self, da):
        a = self.cache[0]
        dz = da * (1 - a) * a
        return dz


class LossFunction:
    def __init__(self, nnModel):
        self.nn = nnModel

    def loss_compute(self, inputs, target):
        raise NotImplementedError

    def __call__(self, inputs, target):
        return self.loss_compute(inputs, target)


class CrossEntropyLoss(LossFunction):
    @staticmethod
    def softmax(z):
        ez = np.exp(z)
        y_hat = ez / np.sum(ez, axis=1).reshape(ez.shape[0], 1)
        return y_hat

    def loss_compute(self, inputs, target):
        y_hat = self.softmax(inputs)
        grad = y_hat - target
        loss = -np.sum(target * np.log(y_hat)) / target.shape[0]
        return Loss(loss, grad, self.nn)


class MSELoss(LossFunction):
    def loss_compute(self, inputs, target):
        grad = 2 * (inputs - target)  # / inputs.shape[0]
        loss = np.mean((inputs - target) ** 2)
        return Loss(loss, grad, self.nn)
