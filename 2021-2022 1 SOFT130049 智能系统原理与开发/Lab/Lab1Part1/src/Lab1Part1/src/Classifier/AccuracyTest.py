from Classifier import *
from DataLoader import *
import pickle

model_save = open('D://ProgramsTest//Python//ISPP//Lab1Part1//src//Classifier//model', 'rb')
model = pickle.load(model_save)

test_path = "D://ProgramsTest//Python//ISPP//Lab1Part1//src//Classifier//train_data//test"
test_data = TestData(test_path)
m_test = test_data.__len__()
test_loader = DataLoader(test_data, batch_size=128)
test_accuracy = []

tn = 0
for xv, yv in test_loader:
    yv_hat = model(xv, eval_pattern=True)
    tn += accuracy(yv_hat, yv, 12)
test_accuracy.append(tn / m_test)
print('Test accuracy:{:.2%}'.format(test_accuracy[-1]))
