% 读取原图文件
I = imread('satomi.jpg');

% 叠加密度为0.04的椒盐噪声。
J = imnoise(I, 'salt & pepper', 0.04); 

% 使用窗口大小为3×3的中值滤波去噪
% 由于函数medfilte2只支持二维矩阵，所以分为R、G、B三个维度进行
filter1(:, :, 1) = medfilt2(J(:, :, 1), [3 3]);
filter1(:, :, 2) = medfilt2(J(:, :, 2), [3 3]);
filter1(:, :, 3) = medfilt2(J(:, :, 3), [3 3]);

% 使用窗口大小为3×3的均值滤波去噪
h = fspecial('average', [3 3]);
filter2 = imfilter(J, h);

% 展示原图片、加椒盐噪音后的图片、中值滤波后的图片、均值滤波后的图片
figure
subplot(2, 2, 1), imshow(I), title("原图");
subplot(2, 2, 2), imshow(J), title("加椒盐噪音");
subplot(2, 2, 3), imshow(filter1), title("去噪1");                    
subplot(2, 2, 4), imshow(filter2), title("去噪2");