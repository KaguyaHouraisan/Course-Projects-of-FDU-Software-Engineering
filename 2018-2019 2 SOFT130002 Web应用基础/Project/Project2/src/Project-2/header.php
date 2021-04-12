<?php
require_once 'core/init.php';
$user = new User();
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Art Store</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/script.js"></script>
    <link rel="shortcut icon" href="resources/icon.jpg">
</head>

<body>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">聖翔音楽学園</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.php">聖翔音楽学園</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="index.php" id="home">首页</a>
                    </li>
                    <li>
                        <a href="details.php?id=446" id="details" title="详情">详情</a>
                    </li>
                    <li style="padding-top: 2% !important;">
                        <form class="form-inline" role="search" action="searchResult.php" method="post">
                            <div class="input-group">
                                <label class="sr-only" for="search">Search</label>
                                <input type="text" class="form-control" placeholder="Search" name="search">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                                </span>
                            </div>
                        </form>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <?php
                    if($user->isLoggedIn()) {
                        ?>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><?php echo $user->data()->username; ?><span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="myOrder.php">我购买的艺术品</a></li>
                                <li><a href="mySelling.php">我卖出的艺术品</a></li>
                                <li><a href="myRelease.php">我发布的艺术品</a></li>
                                <li><a href="myBalance.php">我的余额</a></li>
                                <li><a href="change.php">发布艺术品</a></li>
                                <li class="divider"></li>
                                <li><a href="userInfo.php">个人信息</a></li>
                            </ul>
                        </li>
                        <?php
                    }
                    ?>
                    <li><a href="shoppingCart.php"><span class="glyphicon glyphicon-shopping-cart"><span class="badge" id="cart">购物车</span></span></a></li>
                    <li>
                        <?php
                        if($user->isLoggedIn()) {
                            echo '<a href="logout.php"><span class="glyphicon glyphicon-log-out"><span class="badge">退出登录</span></span></a>';
                        } else {
                            echo '<a href="login.php"><span class="glyphicon glyphicon-log-in"><span class="badge">登录/注册</span></span></a>';
                        }
                        ?>
                    </li>
                </ul>
            </div>
        </div>

        <div class="container-fluid" style="height: 25px">
            <ul class="nav navbar-nav navbar-left" style="height: 25px">
                <li style="font-size: medium">足迹：</li>
                <?php
                $con = mysqli_connect('localhost','phoenix','1049776188','phoenix');
                $sql = "SELECT * FROM foot ORDER BY id";
                $result = $con->query($sql);
                if ($result->num_rows > 0) {
                    // 输出数据
                    while($row = $result->fetch_assoc()) {
                        ?>
                        <li>
                            <a style="padding-top: 0" href="<?= $row["name"] ?>.php"><span class="badge"><?= $row["name"] ?></span></a>
                        </li>
                        <li>  >>  </li>
                <?php
                    }
                }
                ?>
                <li><a style="padding-top: 0">now</a></li>
            </ul>
        </div>
    </nav>
