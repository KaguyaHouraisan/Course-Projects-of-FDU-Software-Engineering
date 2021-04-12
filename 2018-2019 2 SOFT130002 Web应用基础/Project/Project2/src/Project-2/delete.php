<?php

$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

$sql = "DELETE FROM carts WHERE cartID = {$_GET['id']}";

if ($con->query($sql)) {
    echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: blue\">删除成功～</h1></div>";
} else {
    echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: red\">网络错误！</h1></div>";
}

echo "<script type=\"text/javascript\">setTimeout(\"window.location.href='shoppingCart.php'\",500);</script>";