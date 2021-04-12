<?php

$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

$sql = "SELECT * FROM carts WHERE userID = {$_GET['id']}";
$result = $con->query($sql);

//更新订单
$sql = "INSERT INTO orders VALUES (null , {$_GET['id']}, {$_GET['sum']}, CURRENT_TIMESTAMP )";
$con->query($sql);

$sql = "SELECT * FROM orders ORDER BY orderID DESC LIMIT 1";
$re = $con->query($sql)->fetch_assoc();
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $sql = "SELECT * FROM artworks WHERE artworkID = {$row['artworkID']}";
        $artistRes = $con->query($sql);
        while ($artistRow = $artistRes->fetch_assoc()) {
            if ($artistRow['orderID'] > 0) echo "<h1 style='color: red'> 已售出的艺术品将不会被结算！ </h1>";
            else {
                //更新购物车
                $sql = "DELETE FROM carts WHERE artworkID = {$row['artworkID']} AND userID = {$_GET['id']}";
                $con->query($sql);
                //更新艺术品信息
                $sql = "UPDATE artworks SET orderID = {$re['orderID']} WHERE artworkID = {$row['artworkID']}";
                $con->query($sql);
            }
        }
    }
}

//更新余额
$sql = "SELECT * FROM users WHERE id = {$_GET['id']}";
$temp = $con->query($sql)->fetch_assoc();
$value = $temp['balance'] - $_GET['sum'];
$sql = "UPDATE users SET balance = {$value} WHERE id = {$_GET['id']}";
$con->query($sql);

/*
$sql = "SELECT * FROM users WHERE id = {}";
$temp_2 = $con->query($sql)->fetch_assoc();
$value_2 = $temp_2['balance'] + $_GET['sum'];
$sql = "UPDATE users SET balance = {$value_2} WHERE id = {}";
$con->query($sql);
*/
echo "<script type=\"text/javascript\">setTimeout(\"window.location.href='shoppingCart.php'\",500);</script>";