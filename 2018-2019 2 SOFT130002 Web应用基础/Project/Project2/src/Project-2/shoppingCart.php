<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'shoppingCart'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'shoppingCart')";
mysqli_query($con,$sql);

require_once 'header.php';
if(!$user->isLoggedIn()) {
    Redirect::to('login.php');
}

$temp = $user->data()->id;
$sql = "SELECT * FROM carts WHERE userID = {$temp}";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // 输出数据
    ?>

    <div class="container">
        <div id="result" class='row'>
            <table class='table table-bordered table-hover'>
                <tr>
                    <th>图片</th>
                    <th>名称</th>
                    <th>简介</th>
                    <th>价格</th>
                    <th>详情</th>
                    <th>删除</th>
                    <th>备注</th>
                </tr>

                <?php
                $sum = 0;
                while($row = $result->fetch_assoc()) {
                    $sql = "SELECT * FROM artworks WHERE artworkID = {$row['artworkID']}";
                    $artistRes = $con->query($sql);
                    while($artistRow = $artistRes->fetch_assoc()) {
                        ?>

                        <tr>
                            <td style='width: 15%'><img src='resources/img/<?= $artistRow['imageFileName'] ?>' class='img-responsive' style='height: 15%'></td>
                            <td><?= $artistRow['title'] ?></td>
                            <td><?= $artistRow['description'] ?></td>
                            <td><?= $artistRow['price'] ?></td>
                            <td><a href='details.php?id=<?= $artistRow['artworkID'] ?>'><button class='btn btn-primary btn-block'>Learn More</button></a></td>
                            <td><a href="delete.php?id=<?= $row['cartID'] ?>"><button class='btn btn-primary btn-block'>Delete</button></a></td>

                            <?php
                            if ($artistRow['orderID'] > 0) {
                                echo "<td style='color: red'> 艺术品已售出！ </td>";
                            } else {
                                if ($artistRow['timeReleased'] > $row['timeHappened']) {
                                    $sum = $sum + $artistRow['price'];
                                    echo "<td style='color: red'> 艺术品信息变动！ </td>";
                                } else {
                                    $sum = $sum + $artistRow['price'];
                                    echo "<td></td>";
                                }
                            }
                            ?>
                        </tr>

                        <?php
                    }
                }
                ?>
            </table>

            <table class='table table-bordered table-hover'>
                <tr>
                    <td>总价：$ <?= $sum ?></td>

                    <?php

                    if ($user->data()->balance >= $sum) {
                        if ($sum == 0) {
                            ?>
                            <td><button class='btn btn-primary btn-block' disabled>对不起，当前没有可下单的物品！</button></a></td>
                            <?php
                        } else {
                            ?>
                            <td><a href="check.php?id=<?= $user->data()->id ?> &sum=<?= $sum ?>"><button class='btn btn-primary btn-block'>Check!</button></a></td>
                            <?php
                        }
                    } else {
                        ?>
                        <td><button class='btn btn-primary btn-block' disabled>对不起，您当前的余额不足，请充值！</button></a></td>
                        <?php
                    }
                    ?>
                </tr>
            </table>
                <?php
                ?>

        </div>
    </div>

    <?php
} else {
    echo "<h2 style='padding-left: 20%'>您还没有添加艺术品到购物车哦！</h2>";
}

require_once "footer.php";
?>
