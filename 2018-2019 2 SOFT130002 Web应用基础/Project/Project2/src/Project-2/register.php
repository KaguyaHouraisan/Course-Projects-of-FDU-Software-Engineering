<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'register'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'register')";
mysqli_query($con,$sql);

require_once "header.php";
if($user->isLoggedIn()) {
    Redirect::to('index.php');
}

if(Input::exists()) {
    $sql = "SELECT * FROM users WHERE username = '{$_POST['username']}'";
    if($con->query($sql)->fetch_assoc()) {
        echo "<script type=\"text/javascript\">setTimeout(\"window.location.href='register.php'\",1000);</script>";
        echo "<div class=\"middle-box\" style=\"height: 30% !important; visibility: 100%\"><h1 style=\"color: red\">用户名已存在！</h1></div>";
    } else {
        if(Token::check(Input::get('token'))) {
            $validate = new Validate();
            $validation = $validate->check($_POST, false, array(
                'username' => array(
                    'required' => true,
                    'min' => 2,
                    'max' => 20,
                    'unique' => 'users'
                ),
                'password' => array(
                    'required' => true,
                    'min' => 6
                ),
                'password_again' => array(
                    'required' => true,
                    'matches' => 'password'
                ),
                'email' => array(
                    'required' => true,
                ),
                'address' => array(
                    'required' => true,
                ),
                'mobile' => array(
                    'required' => true,
                ),
            ));

            if($validation->passed()) {
                $user = new User();
                $salt = Hash::salt(32);
                try {
                    $user->create(array(
                        'username' => Input::get('username'),
                        'password' => Hash::make(Input::get('password'), $salt),
                        'salt' => $salt,
                        'joined' => date('Y-m-d H:i:s'),
                        'email' => Input::get('email'),
                        'address' => Input::get('address'),
                        'mobile' => Input::get('mobile')
                    ));

                    echo "<script type=\"text/javascript\">setTimeout(\"window.location.href='login.php'\",1000);</script>";
                    echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: blue\">注册成功～</h1></div>";

                } catch(Exception $e) {
                    die($e->getMessage());
                }
            } else {
                echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: red\">注册失败！</h1></div>";
                foreach($validation->errors() as $error) {
                    echo $error, '<br>';
                }
                echo "<script type=\"text/javascript\">setTimeout(\"window.location.href='register.php'\",1000);</script>";
            }
        }
    }
}
?>

    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/common.css">

    <!-- 内容部分 -->
    <div class="content">
        <!-- 登录注册 -->
        <div class="middle-box">
            <form class="login-box" action="" method="post" >
                <P class="title"><i class="icon iconfont icon-int icon-logo"><img src="resources/icon_2.png" style="width: 35%; height: 35%"></i></P>
                <div class="login-input">
                    <i class="icon iconfont icon-people icon-input"></i>
                    <input type="text" name="username" placeholder="请输入用户名（至少6位，不能为纯数字/字母）"
                           class="username" value="<?php echo escape(Input::get('name')); ?>">
                </div>

                <div class="login-input">
                    <i class="icon iconfont icon-lock icon-input"></i>
                    <input type="password" name="password" placeholder="请输入密码（至少6位，不能为纯数字/字母或与用户名相同）"
                           class="password" pattern="^(?![^A-Za-z]+$)(?![^0-9]+$)[\x21-x7e]{6,}$">
                </div>

                <div class="login-input-re">
                    <i class="icon iconfont icon-lock icon-input"></i>
                    <input type="password" name="password_again" placeholder="请再次输入密码"
                           class="rePassword" pattern="^(?![^A-Za-z]+$)(?![^0-9]+$)[\x21-x7e]{6,}$">
                </div>

                <div class="login-input-email">
                    <i class="icon iconfont icon-people icon-input"></i>
                    <input type="text" name="email" placeholder="请输入邮箱"
                           class="email" pattern="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" value="<?php echo escape(Input::get('email')); ?>" >
                </div>

                <div class="login-input-place">
                    <i class="icon iconfont icon-people icon-input"></i>
                    <input type="text" name="address" placeholder="请输入地址"
                           class="address" value="<?php echo escape(Input::get('address')); ?>">
                </div>

                <div class="login-input-place">
                    <i class="icon iconfont icon-people icon-input"></i>
                    <input type="text" name="mobile" placeholder="请输入电话"
                           class="place" pattern="^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$" value="<?php echo escape(Input::get('mobile')); ?>">
                </div>

                <input class="row col-md-7" type="hidden" name="token" value="<?php echo Token::generate(); ?>">
                <button class="login-bt" type="submit">注册</button>

                <div class="login-type">
                    <p>
                        <span class="type-text">已有账号？</span><span class="login-change"><a href="login.php">登录</a></span>
                    </p>
                </div>
            </form>
        </div>
    </div>
