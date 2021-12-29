from torchvision import datasets
from torchvision import transforms
from torch.utils.data import DataLoader
from tqdm import tqdm
import torch
from Model import Model, accuracy_count, setup_seed

test_path = 'D://ProgramsTest//Python//ISPP//Lab1Part2//src//Classifier//train_data//test'
model_path = 'D://ProgramsTest//Python//ISPP//Lab1Part2//src//Classifier//model//model.pth'
setup_seed(13)
device = torch.device('cuda:0' if torch.cuda.is_available() else 'cpu')
model = torch.load(model_path).to(device)
test_data = datasets.ImageFolder(test_path, transform=transforms.Compose(
    [transforms.ToTensor(), transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])])
                                 )
test_set = DataLoader(test_data, shuffle=True)
correct_num = 0

for img, label in tqdm(test_set):
    img, label = img.to(device), label.to(device)
    correct_num += accuracy_count(x=model(img), label=label)
print('Test accuracy:\000\000{:.2%}'.format(correct_num / test_data.__len__()))
