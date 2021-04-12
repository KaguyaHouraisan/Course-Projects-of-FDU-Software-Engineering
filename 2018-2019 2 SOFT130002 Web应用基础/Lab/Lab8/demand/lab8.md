# Lab08 - PHP

## 简介

本次 Lab 主要涉及 服务器环境的配置 && PHP 的基础使用

## 关于如何安装XMAPP
还未安装好的同学请看[此视频](https://mooc1-1.chaoxing.com/nodedetailcontroller/visitnodedetail?courseId=203371380&knowledgeId=147185698)！

## 要求

1. 本次 lab 不要求 `css` \\ `js` \\ `html` 的编写，我们已经提供了其它必需的文件，但你可以根据你的需要决定是否修改这些文件
2. 请在 `Lab08.php` 的合适位置，使用 `<?php include 'xxx.php'; ?>` 的方式导入 `header.inc.php` 和 `left.inc.php`
3. 完成subTotal,shipping,grandTotal各自的计算，数据在`data.inc.php`中
4. 完成 `functions.inc.php` 中的 `function outputOrderRow($file, $title, $quantity, $price)` 函数

最终效果请参考 `Lab08.html`

## 注意

在测试 Lab 时，请先开启 `Apache` 服务，并将相应文件\\文件夹移动到 `htdocs` 目录下

Windows 下的 `htdocs` 位于 `X:\your_path\xampp\htdocs`

macOS 下的 `htdocs` 位于 `/Applications/MAMP/htdocs/`

比如将 `lab8` 文件夹移动到了 `htdocs` 文件夹下，那么可以通过访问 `http://localhost/lab8/Lab08.php` 的方式访问其中的 `Lab08.php` 文件

当然你们可以通过参考网上教程的方式修改 `Apache` 服务器的根目录，如有需求请自行参考相应教程

## 参考

1. Fundamentals of Web Development. Chapter 11.

## 测试

本次 Lab 没有唯一答案，只要求最后的效果图与 `Lab08.html` 效果类似即可

## 素材

本次 Lab 素材已放到课程对应的 Lab 仓库上

关于同步的问题，请参考同步教程 [Github进行fork后如何与原仓库同步](https://blog.csdn.net/chenyufeng1991/article/details/49276855)

## 提交

本次 Lab 请提交到 github 已有的 Lab 仓库上，无需额外创建新的 Lab 仓库，仓库的结构请参考如下：

* `[Your GitHub Repo]`
    * `css` css folder
    * `images` images folder
    * `functions.inc.php`
    * `header.inc.php`
    * `Lab08.php` Lab08.php
    * `left.inc.php`
    * `data.inc.php`
    * `xxx.xxx` other files

## 截止日期

**2019-05-22 23:59:59 GMT+08 (中国标准时间)**

## Lab 相关

有任何疑问欢迎联系助教：

> Email:
>
> [王婕](mailto:veronicadavichi@outlook.com)
>
> [吴豪奇](mailto:hqwu15@fudan.edu.cn)
>
> [花楠](mailto:15302010013@fudan.edu.cn)
>
> Or 微信群上戳一戳