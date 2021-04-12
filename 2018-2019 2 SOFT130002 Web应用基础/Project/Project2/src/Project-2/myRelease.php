<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'myRelease'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'myRelease')";
mysqli_query($con,$sql);

require_once 'header.php';
if(!$user->isLoggedIn()) {
    Redirect::to('login.php');
}

$temp = $user->data()->id;
$sql = "SELECT * FROM artworks WHERE ownerID = {$temp}";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // 输出数据
    ?>

    <div class="container">
        <div id="result" class='row'>
            <table class='table table-bordered table-hover'>
                <tr>
                    <th>艺术品</th>
                    <th>上传时间</th>
                    <th>修改</th>
                    <th>删除</th>
                    <th>备注</th>
                </tr>

                <?php
                while($row = $result->fetch_assoc()) {
                    ?>
                    <tr>
                        <td><a href='details.php?id=<?= $row['artworkID'] ?>'><?= $row['title'] ?></a></td>
                        <td><?= $row['timeReleased'] ?></td>
                        <?php
                        if ($row['orderID'] > 0) {
                            ?>
                            <td><button class='btn btn-primary btn-block' disabled>Change</button></td>
                            <td><button class='btn btn-primary btn-block' disabled>Delete</button></td>
                            <td><h5 style='color: red'>已售出！</h5></td>
                            <?php
                        } else {
                            ?>
                            <td><a href='change.php?artworkID=<?= $row['artworkID'] ?>'><button class='btn btn-primary btn-block'>Change</button></a></td>
                            <td><a href='deleteArtwork.php?artworkID=<?= $row['artworkID'] ?>' onclick='return del();'><button class='btn btn-primary btn-block'>Delete</button></a></td>
                            <script>
                                function del()
                                {
                                    return confirm("确定要删除吗？");
                                }
                            </script>
                            <td></td>
                            <?php
                        }
                        ?>
                    </tr>
                    <?php
                }
                ?>
            </table>
        </div>
    </div>

    <?php
} else {
    echo "<h2 style='padding-left: 20%'>您还没有上传过艺术品哦！</h2>";
}

require_once "footer.php";
?>
