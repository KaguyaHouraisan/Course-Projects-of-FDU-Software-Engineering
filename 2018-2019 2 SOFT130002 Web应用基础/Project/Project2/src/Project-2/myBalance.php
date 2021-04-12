<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'myBalance'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'myBalance')";
mysqli_query($con,$sql);

require_once 'header.php';
if(!$user->isLoggedIn()) {
    Redirect::to('login.php');
}

if ($_POST) {
    $value = $user->data()->balance + $_POST['balance'];
    $sql = "UPDATE users SET balance = {$value} WHERE id = {$user->data()->id}";
    $con->query($sql);
    Redirect::to('myBalance.php');
}

?>

<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/common.css">

        <div class="middle-box">
            <form class="form-group-lg login-box" action="myBalance.php" method="post">
                <P class="title"><i class="icon iconfont icon-int icon-logo"><img src="resources/icon_2.png" style="width: 35%; height: 35%"></i></P>
                <div class="login-input">
                    <label class="username">您的余额: $<input type="text" disabled value="<?php echo escape($user->data()->balance); ?>"></label><br>
                </div>

                <div class="login-input">
                    <i class="icon iconfont icon-lock icon-input"></i>
                    <input type="text" placeholder="请输入您想充值的金额: （正整数）" class="username" name="balance" pattern="^\+?[1-9][0-9]*$">
                </div>

                <button class="btn btn-success login-bt" type="submit" name="submit">充值</button>
            </form>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>
