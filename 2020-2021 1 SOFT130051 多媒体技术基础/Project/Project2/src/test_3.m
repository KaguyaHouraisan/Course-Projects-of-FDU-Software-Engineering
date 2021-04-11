imname = "grayed_satomi.jpg";

% 读取原图文件
I = imread(imname);

% 灰度拉伸
J = imadjust(I, [0.2, 0.6], [0, 1]);

% 直方图均衡化
H = histeq(I);

% 展示原图片、灰度拉伸后的图片、直方图均衡化后的图片，以及各自对应的灰度分布直方图
figure
subplot(2, 3, 1), imshow(I), title("原图");
subplot(2, 3, 2), imshow(J), title("灰度拉伸");
subplot(2, 3, 3), imshow(H), title("直方图均衡化");
subplot(2, 3, 4), imhist(I), title("灰度分布直方图-原图");
subplot(2, 3, 5), imhist(J), title("灰度分布直方图-灰度拉伸");
subplot(2, 3, 6), imhist(H), title("灰度分布直方图-直方图均衡化");