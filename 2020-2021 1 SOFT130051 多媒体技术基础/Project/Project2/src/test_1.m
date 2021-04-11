% 输入图像文件的路径
impath = input('image:', 's');

% 输入横坐标x
px = input('x:');

% 输入纵坐标y
py = input('y:');

% 读取路径对应的文件
I = imread(impath);

% 获取文件大小，横轴长度为x，纵轴长度为y，z代表用几个参数描述一个像素（这里为3，分别为R，G，B）
[y, x, z] = size(I);

% 通过max、min函数检查边界，获取(x,y)及周围8个点坐标的RGB值
for fx = max(px - 1, 1) : min(px + 1, x)
    for fy = max(py - 1, 1) : min(py + 1, y)
        fprintf('(%d, %d): (%d, %d, %d)\n', fx, fy, I(fy, fx, 1), I(fy, fx, 2), I(fy, fx, 3));
    end
end