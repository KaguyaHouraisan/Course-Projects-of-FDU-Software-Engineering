LAB 6作业要求
==========
本 LAB 只提供初始代码，需要通过修改初始代码使得最终网页达到要求的展示效果。

-------------------

## 作业提交 HOW TO SUBMIT
### 作业提交网址 ADDRESS
//github.com/(Your ID)/**fdu-18ss-web-lab**

e.g. //github.com/veronicadavichi/**fdu-18ss-web-lab**

即上传到你 lab6 的仓库中

-------------------

### 文件结构 FILE STRUCTURE
`root` 即直接打开仓库所见的层，或写作 `/lab6`。

```
/lab6
	/lab6.html
	/lab6.css
	/lab6.js
	/images
	/lab6 作业要求.md
```

`lab6.js` 是<u>**唯一**</u>需要修改的页面，主要是<u>**Javascript操作Dom对象**</u>。

本Lab涉及到了**JavaScript中的对象样式与事件绑定**，参考教材《Fundamentals of Web Development, 2nd Edition》相关章节。

-------------------

## 作业要求

1.所有事件处理函数必须等到页面加载完成之后才生效。

2.你需要给 `thumbnail` 中的图片绑定事件，当小图片被点击时，上面的大图片将会变成相对应的图片。

3.给 `<figure>` 元素绑定事件。当鼠标移入上方大图的时候， `<figcaption>` 元素会逐渐淡入，最终透明度为80%，并且其中的文字需要变成对应小图的标题。当鼠标移出大图的时候， `<figcaption>` 元素会逐渐淡出，最终透明度为0。淡入淡出的动画时长皆为1秒。

# DDL

截止时间为**2019年5月2日23:59:59**，也就是下周周四。
