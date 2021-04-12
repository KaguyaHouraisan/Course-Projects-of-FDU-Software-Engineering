<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'myOrder'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'myOrder')";
mysqli_query($con,$sql);

require_once 'header.php';
if(!$user->isLoggedIn()) {
    Redirect::to('login.php');
}

$temp = $user->data()->id;
$sql = "SELECT * FROM orders WHERE custID = {$temp}";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // 输出数据
    while($row = $result->fetch_assoc()) {
        ?>

        <div class="container">
            <div id="result" class='row'>
                <table class='table table-bordered table-hover'>
                    <tr>
                        <th>订单编号</th>
                        <th>订单时间</th>
                        <th>总金额</th>
                    </tr>

                    <tr>
                        <td><?= $row['orderID'] ?></td>
                        <td><?= $row['timeCreated'] ?></td>
                        <td><?= $row['sum'] ?></td>
                    </tr>

                    <tr>
                        <th>艺术品</th>
                        <th>单价</th>
                        <th>详情</th>
                    </tr>

                    <?php
                    $sql = "SELECT * FROM artworks WHERE orderID = {$row['orderID']}";
                    $artistRes = $con->query($sql);
                    while($artistRow = $artistRes->fetch_assoc()) {
                        ?>

                        <tr>
                            <td><?= $artistRow['title'] ?></td>
                            <td><?= $artistRow['price'] ?></td>
                            <td><a href='details.php?id=<?= $artistRow['artworkID'] ?>'><button class='btn btn-primary btn-block'>Learn More</button></a></td>
                        </tr>

                        <?php
                    }
                    ?>
                </table>
            </div>
        </div>

<?php
    }
} else {
    echo "<h2 style='padding-left: 20%'>您还没有购买过艺术品哦！</h2>";
}

require_once "footer.php";
?>
