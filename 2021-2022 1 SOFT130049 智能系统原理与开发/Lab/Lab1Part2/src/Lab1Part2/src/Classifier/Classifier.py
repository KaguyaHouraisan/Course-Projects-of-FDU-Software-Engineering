# coding=utf-8
from torch import nn, optim
from torchvision import datasets
from torchvision import transforms
from torch.utils.data import DataLoader
from tqdm import tqdm
import torch
from Model import Model, accuracy_count, setup_seed

setup_seed(7)
train_path = 'D://ProgramsTest//Python//ISPP//Lab1Part2//src//Classifier//train_data//train'
test_path = 'D://ProgramsTest//Python//ISPP//Lab1Part2//src//Classifier//train_data//test'
model_path = 'D://ProgramsTest//Python//ISPP//Lab1Part2//src//Classifier//model//model.pth'
train_data = datasets.ImageFolder(train_path, transform=transforms.Compose([transforms.RandomRotation(15),
                                                                            transforms.ToTensor(),
                                                                            transforms.Normalize(
                                                                                mean=[0.485, 0.456, 0.406],
                                                                                std=[0.229, 0.224, 0.225])]))
test_data = datasets.ImageFolder(test_path, transform=transforms.Compose([transforms.ToTensor(),
                                                                          transforms.Normalize(
                                                                              mean=[0.485, 0.456, 0.406],
                                                                              std=[0.229, 0.224, 0.225])]))
train_set = DataLoader(train_data, shuffle=True, batch_size=64)
test_set = DataLoader(test_data, shuffle=True)
num_train = train_data.__len__()
num_test = test_data.__len__()
device = torch.device('cuda:0' if torch.cuda.is_available() else 'cpu')
model = Model().to(device)

epochs = 100  # 迭代次数
lr = 0.01  # 学习率

criterion = nn.CrossEntropyLoss()
optimizer = optim.SGD(model.parameters(), lr=lr)
max_accuracy = 0.9  # 当测试集上准确率小于90%时不会保存模型，以提高性能
for epoch in range(epochs):
    sum_of_loss = 0
    correct_num_train = 0
    correct_num_test = 0
    for img, label in tqdm(train_set):
        img, label = img.to(device), label.to(device)
        y_hat = model(img)
        loss = criterion(y_hat, label)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()
        sum_of_loss += loss.item()
        correct_num_train += accuracy_count(x=y_hat, label=label)

    with torch.no_grad():
        for img, label in tqdm(test_set):
            img, label = img.to(device), label.to(device)
            y_hat = model(img)
            correct_num_test += accuracy_count(x=y_hat, label=label)
        if max_accuracy < correct_num_test / num_test:
            max_accuracy = correct_num_test / num_test
            torch.save(model, model_path)
    print('Round:{}\000\000Train accuracy:{:.2%}\000\000Test accuracy:{:.2%}'.format(
        epoch, correct_num_train / num_train, correct_num_test / num_test))
