/****************** 页面公有的方法 ***********************/

// 导航
(function(){
    // 得到参数
    var userKey = sessionStorage.getItem('userKey');
    var loginNot = document.getElementsByClassName('login-not')[0];
    var loginSuccess = document.getElementsByClassName('login-success')[0];
    var loginSuccessUsername = document.getElementsByClassName('login-success-username')[0];

    // 显示关于用户的信息
    showUserAbout();
    function showUserAbout() {
        // 如果能得到用户的标识，则表示用户登录成功，这个时候可以对用户信息做处理
        if (userKey) {
            var userInfo = localStorage.getItem('userInfo');
            var userInfoList = JSON.parse(userInfo);
            console.log(userInfoList[userKey]);
            loginSuccess.style.display = 'block';
            loginSuccessUsername.innerHTML = userInfoList[userKey].username;
        } else {
            loginNot.style.display = 'block';
        }
    }
})();

// 退出登录的方法
(function(){
    // 得到参数
    var loginOut = document.getElementsByClassName('login-out')[0];

    // 容错处理
    if (!loginOut) {
        return;
    }

    // 点击退出登录按钮，清楚用户标识
    loginOut.onclick = function() {
        window.open('../login/login.html','_self');
        sessionStorage.removeItem('userKey')
    }
})();

// 打开个人主页的方法
(function(){
    // 得到参数
    var loginUsername = document.getElementsByClassName('login-success-username')[0];
    var loginOut = document.getElementsByClassName('login-out')[0];

    // 容错处理
    if (!loginOut) {
        return;
    }

    loginUsername.onclick = function() {
        window.open('../personalHomePage/personalHomePage.html','_self');
    }
})();

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

// 加载动画 接收一个布尔值，用来判断创建动画还是删除动画
function globalLoading (Boole) {
    if (Boole) {
        // 创建一个元素
        var dom = document.createElement('div');
        dom.className = 'global-loading';
        dom.innerHTML = '<div></div>';

        // 创建一个遮罩层
        var mask = document.createElement('div');
        mask.className = 'global-mask';

        // 插入到页面中
        document.body.appendChild(mask);
        document.body.appendChild(dom);
    } else {
        // 从页面中删除
        var mask = document.getElementsByClassName('global-mask')[0];
        var dom = document.getElementsByClassName('global-loading')[0];
        document.body.removeChild(mask);
        document.body.removeChild(dom);
    }
}

(function(){
    //页面切换动画
    change();
    function change() {
        // 创建一个遮罩层
        var mask = document.createElement('div');
        mask.className = 'global-mask';
        mask.style.backgroundColor = 'white';
        mask.style.opacity = '1';

        // 插入到页面中
        document.body.appendChild(mask);

        // 计时器
        setTimeout(function() {
            mask.style.width = '95%';
        }, 50);

        // 计时器
        setTimeout(function() {
            mask.style.width = '90%';
        }, 100);

        // 计时器
        setTimeout(function() {
            mask.style.width = '85%';
            mask.style.backgroundColor = 'red';
        }, 150);

        // 计时器
        setTimeout(function() {
            mask.style.width = '80%';
        }, 200);

        // 计时器
        setTimeout(function() {
            mask.style.width = '75%';
        }, 250);

        // 计时器
        setTimeout(function() {
            mask.style.width = '70%';
            mask.style.backgroundColor = 'yellow';
        }, 300);

        // 计时器
        setTimeout(function() {
            mask.style.width = '65%';
        }, 350);

        // 计时器
        setTimeout(function() {
            mask.style.width = '60%';
        }, 400);

        // 计时器
        setTimeout(function() {
            mask.style.width = '55%';
            mask.style.backgroundColor = 'blue';
        }, 450);

        // 计时器
        setTimeout(function() {
            mask.style.width = '50%';
        }, 500);

        // 计时器
        setTimeout(function() {
            mask.style.width = '45%';
        }, 550);

        // 计时器
        setTimeout(function() {
            mask.style.width = '40%';
            mask.style.backgroundColor = 'green';
        }, 600);

        // 计时器
        setTimeout(function() {
            mask.style.width = '35%';
        }, 650);

        // 计时器
        setTimeout(function() {
            mask.style.width = '30%';
        }, 700);

        // 计时器
        setTimeout(function() {
            mask.style.width = '25%';
            mask.style.backgroundColor = 'black';
        }, 750);

        // 计时器
        setTimeout(function() {
            mask.style.width = '20%';
        }, 800);

        // 计时器
        setTimeout(function() {
            mask.style.width = '15%';
        }, 850);

        // 计时器
        setTimeout(function() {
            mask.style.width = '10%';
            mask.style.backgroundColor = 'white';
        }, 900);

        // 计时器
        setTimeout(function() {
            mask.style.width = '5%';
        }, 950);

        // 计时器
        setTimeout(function() {
            mask.style.width = '0';
        }, 1000);

        // 计时器
        setTimeout(function() {
            document.body.removeChild(mask);
        }, 2000);
    }

    // 监听页面的变化
    window.addEventListener('resize', function() {
        change();
    })
})();