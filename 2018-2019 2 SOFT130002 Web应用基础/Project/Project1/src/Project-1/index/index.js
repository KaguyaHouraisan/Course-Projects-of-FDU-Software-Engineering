(function(){
    // 旋转动画
    rotate();
    function rotate(){
        // 得到参数
        var imgs = document.getElementsByClassName('img-item');
        var width = imgs[0].clientWidth;

        //得到图片到圆心的距离 + 20间隔
        var distance = (width / 2) / Math.tan (20 / 180 * Math.PI) + 20;

        // 外盒子的选择参数
        var y = 1;

        // 循环图片 得到图片位置
        for (var i = 0, len = imgs.length; i<len; i++) {
            imgs[i].style.transform = " rotateY("+ i * 40 + "deg) translateZ("+ distance +"px)";
            !function(i){
                imgs[i].onclick = function(){
                    var box = document.getElementsByClassName("box")[0];
                    box.style.transform = " rotateY("+ y * 40 +"deg)";
                    for (var z = 0,zlen = imgs.length; z < zlen; z++) {
                        var x = (z * 40 + 40);
                        if(x === 360){
                            x = 0;
                        }
                        imgs[z].style.transform = " rotateY("+ x +"deg) translateZ("+ distance +"px)";
                    }
                    y++;
                }
            }(i);
        }
    }

    // 监听页面的变化
    window.addEventListener('resize', function() {
        rotate();
    })
})();