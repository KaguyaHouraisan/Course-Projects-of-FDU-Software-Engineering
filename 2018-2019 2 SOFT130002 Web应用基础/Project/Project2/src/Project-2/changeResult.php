<?php
require_once 'header.php';

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

$temp = $user->data()->id;
$temp_1 = htmlspecialchars($_POST['artist'], ENT_QUOTES);
$temp_2 = htmlspecialchars($_POST['title'], ENT_QUOTES);
$temp_3 = htmlspecialchars($_POST['description'], ENT_QUOTES);

if ($_POST) {
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $file = $_FILES['upFile'];

        //检查文件类型
        if($file["type"] == 'image/jpg') {
            echo "不能上传此类型文件！";
            exit;
        }

        $filename = $file["tmp_name"];

        if($_POST['type'] == 0) {
            if (!$filename) {
                echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: red\">请上传图片！</h1></div>";
                echo "<script type=\"text/javascript\">setTimeout(\"window.location.href='myRelease.php'\",500);</script>";
            } else {
                $sql = "INSERT INTO artworks VALUES (null , '{$temp_1}', '{$temp_2}', '{$temp_2}', '{$temp_3}', {$_POST['year']}, '{$_POST['style']}', {$_POST['width']}, {$_POST['height']}, {$_POST['price']}, 0, {$temp}, NULL, CURRENT_TIMESTAMP)";

                if ($con->query($sql)) {
                    echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: blue\">加入成功～</h1></div>";
                } else {
                    echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: red\">网络错误！</h1></div>";
                }

                $sql = "SELECT * FROM artworks WHERE title = '{$temp_2}' AND artist = '{$temp_1}' AND description = '{$temp_3}' AND ownerID = {$temp}";
                $row_temp = $con->query($sql)->fetch_assoc();
                $destination = "resources/img/" .$row_temp['artworkID'] . ".jpg";
                if(!move_uploaded_file ($filename, $destination)) {
                    echo "上传文件出错！";
                    exit;
                }

                $imageName = $row_temp['artworkID'] . ".jpg";
                $sql = "UPDATE artworks SET imageFileName = '{$imageName}' WHERE artworkID = {$row_temp['artworkID']}";
                $con->query($sql);
            }
        } else {
            if ($filename) {
                $destination = "resources/img/" .$_POST['type'] . ".jpg";
                unlink($destination);
                if(!move_uploaded_file ($filename, $destination)) {
                    echo "上传文件出错！";
                    exit;
                }
            }

            $sql = "UPDATE artworks SET artist = '{$temp_1}', title = '{$temp_2}', description = '{$temp_3}', yearOfWork = {$_POST['year']}, genre = '{$_POST['style']}', width = {$_POST['width']}, height = {$_POST['height']}, price = {$_POST['price']}, timeReleased = CURRENT_TIMESTAMP WHERE artworkID = {$_POST['type']}";

            if ($con->query($sql)) {
                echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: blue\">修改成功～</h1></div>";
            } else {
                echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: red\">网络错误！</h1></div>";
            }
        }

        echo "<script type=\"text/javascript\">setTimeout(\"window.location.href='myRelease.php'\",500);</script>";
    }
} else {
    Redirect::to('myRelease.php');
}