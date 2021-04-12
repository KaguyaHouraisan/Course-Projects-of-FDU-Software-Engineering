# Lab11 - PHP & Database

## 简介

本次 Lab 主要涉及 PHP 对数据库的操作

## 要求

1. 本次 lab 不要求 `css` \\ `js` \\ `html` 的编写，我们已经提供了其它必需的文件，但你可以根据你的需要决定是否修改这些文件。
2. 请通过 phpMyAdmin 界面将 sql 目录下的 travel-small.sql 数据库文件导入数据库，关于 phpMyAdmin 界面的说明见 [PHP Apache MySQL 环境搭建指南](https://github.com/fudansswebfundamental/Docs/blob/master/PHP%20Apache%20MySQL%20%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA%E6%8C%87%E5%8D%97.md)
3. 请在 `Lab11.php` 有注释提示的位置，插入代码。要求能够实现加载数据与过滤图片的功能。所有的数据从 `Continents` 、 `Countries` 和 `ImageDetails` 三张数据表中获得；相关图片在 `images/square-medium` 目录下。对于数据加载，首先你需要加载所有洲和国家的信息，为过滤器提供选项。在没有使用过滤器之前，呈现所有图片。当选择了洲与国家并点击了Filter按钮之后，你应当只显示符合要求的图片。
4. 关于过滤的实现，最好将相关的功能封装成函数，保持代码的可读性。比如，如果你要根据国家来过滤图片，你可以编写函数 `findByCountry` 来封装相关功能。

## 注意

在测试 Lab 时，请先开启 `Apache` 服务，并将相应文件\\文件夹移动到 `htdocs` 目录下

Windows 下的 `htdocs` 位于 `X:\your_path\xampp\htdocs`

macOS 下的 `htdocs` 位于 `/Applications/MAMP/htdocs/`

## 参考

1. Fundamentals of Web Development. Chapter 14.

## 测试

本次 Lab 没有唯一答案，只要求实现相应的功能即可

## 素材

本次 Lab 素材已放到课程对应的 Lab 仓库上

关于同步的问题，请参考同步教程 [Github进行fork后如何与原仓库同步](https://blog.csdn.net/chenyufeng1991/article/details/49276855)

## 提交

本次 Lab 请提交到 github 已有的 Lab 仓库上，无需额外创建新的 Lab 仓库，仓库的结构请参考如下：

* `[Your GitHub Repo]`
    * `css` css folder
    * `fonts` fonts folder
    * `images` images folder
    * `js` javascript folder
    * `functions.inc.php`
    * `header.inc.php`
    * `Lab11.php` Lab11.php
    * `left.inc.php`
    * `travel-data.inc.php`
    * `xxx.xxx` other files

## 截止日期

**2019-06-12 23:59:59 GMT+08 (中国标准时间)**

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