<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'userInfo'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'userInfo')";
mysqli_query($con,$sql);

require_once 'header.php';
if(!$user->isLoggedIn()) {
    Redirect::to('login.php');
}

?>

<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/common.css">

        <div class="middle-box">
            <div class="form-group-lg login-box">
                <P class="title"><i class="icon iconfont icon-int icon-logo"><img src="resources/icon_2.png" style="width: 35%; height: 35%"></i></P>
                <div class="login-input">
                    <label class="username">用户名:<input type="text" disabled value="<?php echo escape($user->data()->username); ?>"></label><br>
                </div>

                <div class="login-input">
                    <label class="username">Email:<input type="text" disabled value="<?php echo escape($user->data()->email); ?>"></label><br>
                </div>

                <div class="login-input">
                    <label class="username">注册时间:<input type="text" disabled value="<?php echo escape($user->data()->joined); ?>"></label><br>
                </div>

                <div class="login-input">
                    <label class="username">电话:<input type="text" disabled value="<?php echo escape($user->data()->mobile); ?>"></label><br>
                </div>

                <div class="login-input">
                    <label class="username">地址:<input type="text" disabled value="<?php echo escape($user->data()->address); ?>"></label><br>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>