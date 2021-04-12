LAB 7作业要求
==========
本 LAB 只提供初始代码，需要通过修改初始代码使得最终网页达到要求的展示效果。

-------------------

## 作业提交 HOW TO SUBMIT
### 作业提交网址 ADDRESS
//github.com/(Your ID)/**fdu-18ss-web-lab**

e.g. //github.com/veronicadavichi/**fdu-18ss-web-lab**

即上传到你 lab7 的仓库中

-------------------

### 文件结构 FILE STRUCTURE
`root` 即直接打开仓库所见的层，或写作 `/lab7`。

```
/lab7
	/lab7.html
	/lab7.css
	/images
	/lab7 作业要求.md
```

`lab7.html` 是<u>**唯一**</u>需要修改的页面，主要是<u>**jQuery的使用**</u>。

本Lab涉及到了**jQuery对象的操作**，参考教材《Fundamentals of Web Development, 2nd Edition》相关章节。

-------------------

## 作业要求

1.所有事件处理函数必须等到页面加载完成之后才生效。

2.在 `<head>` 中导入自己下载的jQuery文件，也可以使用在线的文件，例如 `http://code.jquery.com/jquery-3.1.0.min.js` 。

3.使用 `jQuery` 来响应右侧缩略图的点击事件。通过替换 ` <figure>` 元素中 `<img>` 元素的 `src` 属性来显示被点击的图片。(提示：获取被点击图片的`src`属性，然后将路径字符串中的 `small` 修改为 `medium` )

4.修改 `figcaption` 使得下方的文字显示为对应图片的说明。说明信息存在于对应缩略图的 `alt` 与 `title` 属性中。

5.为每个滑竿的 `input` 事件设置监听器，你需要根据滑竿实际的值设置滤镜属性 `-webkit-filter` 以及 `filter` 。请自行学习滤镜 `filter` 的使用方法。

6.为重置按钮设置监听事件，当点击按钮时，将滤镜属性设置为 `none` ，以便移除滤镜。

# DDL

截止时间为**2019年5月16日23:59:59**，也就是下周周四。
