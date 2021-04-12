<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'mySelling'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'mySelling')";
mysqli_query($con,$sql);

require_once 'header.php';
if(!$user->isLoggedIn()) {
    Redirect::to('login.php');
}

$temp = $user->data()->id;
$sql = "SELECT * FROM artworks WHERE ownerID = {$temp} AND orderID > 0";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // 输出数据
?>

<div class="container">
    <div id="result" class='row'>
        <table class='table table-bordered table-hover'>
            <tr>
                <th>订单编号</th>
                <th>艺术品</th>
                <th>卖出时间</th>
                <th>价格</th>
                <th>详情</th>
                <th>购买人用户名</th>
                <th>邮箱</th>
                <th>电话</th>
                <th>地址</th>
            </tr>

    <?php
    while($row = $result->fetch_assoc()) {
        $sql = "SELECT * FROM orders WHERE orderID = {$row['orderID']}";
        $artistRes = $con->query($sql);
        while($artistRow = $artistRes->fetch_assoc()) {
        ?>
            <tr>
                <td><?= $row['orderID'] ?></td>
                <td><?= $row['title'] ?></td>
                <td><?= $artistRow['timeCreated'] ?></td>
                <td><?= $row['price'] ?></td>
                <td><a href='details.php?id=<?= $row['artworkID'] ?>'><button class='btn btn-primary btn-block'>Learn More</button></a></td>

                <?php
                $sql = "SELECT * FROM users WHERE id = {$artistRow['custID']}";
                $custRes = $con->query($sql);
                $custRow = $custRes->fetch_assoc();
                ?>

                <td><?= $custRow['username'] ?></td>
                <td><?= $custRow['email'] ?></td>
                <td><?= $custRow['mobile'] ?></td>
                <td><?= $custRow['address'] ?></td>
            </tr>

            <?php
        }
    }
        ?>
        </table>
    </div>
</div>

    <?php
} else {
    echo "<h2 style='padding-left: 20%'>您还没有卖出过艺术品哦！</h2>";
}

require_once "footer.php";
?>
