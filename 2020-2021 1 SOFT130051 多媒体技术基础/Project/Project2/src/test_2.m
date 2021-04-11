imname = "satomi.jpg";

% 读取对应的图片文件，并转换为double型（计算方差时第一个输入的参数必须是单精度或双精度型）
I = im2double(imread(imname));

% 三维空间到一维空间的映射
grayI = 0.29900 * I(:, :, 1) + 0.58700 * I(:, :, 2) + 0.11400 * I(:, :, 3);

% 将转换后的灰度图片保存到当前文件夹
imwrite(grayI, "grayed_" + imname);

% 计算灰度图片的方差（grayI中用[0,1]中的数字表示灰度，实际需要乘255）
v = var(grayI(:) * 255, 1);

fprintf("生成的灰度图片为保存在当前目录下的: grayed_%s\n", imname);
fprintf("转换后灰度图片的方差为: %f\n", v);

% 展示原图片与转换后的灰度图片
figure
subplot(2, 1, 1), imshow(I), title("原图片");
subplot(2, 1, 2), imshow(grayI), title("转换后的灰度图片");
