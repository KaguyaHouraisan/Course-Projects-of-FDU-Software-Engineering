<?php

$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

$sql = "DELETE FROM artworks WHERE artworkID = {$_GET['artworkID']}";
$con->query($sql);

echo "<div class=\"middle-box\" style=\"height: 30% !important;\"><h1 style=\"color: blue\">删除成功～</h1></div>";
echo "<script type=\"text/javascript\">setTimeout(\"window.location.href='myRelease.php'\",500);</script>";