<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'login'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'login')";
mysqli_query($con,$sql);

require_once "header.php";
if($user->isLoggedIn()) {
    Redirect::to('index.php');
}

if(Input::exists()) {
    if(Token::check(Input::get('token'))) {

        $validate = new Validate();
        $validation = $validate->check($_POST, true, array(
            'username' => array('required' => true),
            'password' => array('required' => true)
        ));

        if($validation->passed()) {
            //Log user In
            $remember = (Input::get('remember') === 'on') ? true : false;
            $login = $user->login(Input::get('username'), Input::get('password'), $remember);

        } else {
            foreach($validation->errors() as $error) {
                echo $error . '<br>';
            }
        }
    }
}

?>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/common.css">
    <div class="container">
        <!-- 内容部分 -->
        <div class="content">
            <?php
            if(Session::exists('register')) {
                echo '<div class="alert alert-success" role="alert">' . Session::flash('register') . '</div><br>';
            }
            ?>
            <!-- 登录注册 -->
            <div class="middle-box">
                <form class="login-box" action="" method="post" enctype="multipart/form-data">
                    <P class="title"><i class="icon iconfont icon-int icon-logo"><img src="resources/icon_2.png" style="width: 35%; height: 35%"></i></P>
                    <div class="login-input">
                        <i class="icon iconfont icon-people icon-input"></i>
                        <input type="text" placeholder="请输入用户名: " class="username"
                               name="username" value="<?php echo escape(Input::get('username')); ?>">
                    </div>

                    <div class="login-input">
                        <i class="icon iconfont icon-lock icon-input"></i>
                        <input type="password" placeholder="请输入密码: " class="password"
                               name="password">
                    </div>

                    <div class="login-input">
                        <label><input type="checkbox" name="remember"></label>
                    </div>

                    <input type="hidden" name="token" value="<?php echo Token::generate(); ?>">

                    <button class="btn btn-success login-bt" type="submit" name="submit">登录</button>

                    <div class="login-type">
                        <p>
                            <span class="type-text">没有账号？</span><span class="login-change"><a href="register.php">注册</a></span>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
