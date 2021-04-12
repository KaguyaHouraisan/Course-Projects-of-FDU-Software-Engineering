/****************** 页面公有的方法 ***********************/

// 弹窗提示
function globalTips(text) {
    // 创建一个元素
    var dom = document.createElement("span");
    dom.className = 'global-tips';
    dom.innerHTML = text;

    // 创建一个遮罩层
    var mask = document.createElement('div');
    mask.className = 'global-mask';

    // 插入到页面中
    document.body.appendChild(dom);
    document.body.appendChild(mask);

    // 计时器（两秒后清除dom）
    setTimeout(function() {
        document.body.removeChild(dom);
        document.body.removeChild(mask);
    }, 1000);
}