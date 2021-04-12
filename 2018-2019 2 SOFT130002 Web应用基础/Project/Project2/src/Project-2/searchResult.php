<?php

error_reporting(E_ALL ^ E_NOTICE);

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'searchResult'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'searchResult')";
mysqli_query($con,$sql);

require_once 'header.php';

if ($_POST["search"]) {
    $searchText = $_POST["search"];
} else {
    $searchText = $_GET["searchText"];
}

$T_1 = 'orderID';$T_2 = 'orderID';$T_3 = 'orderID';$T_4 = 'orderID';$T_5 = 'orderID';$T_6 = 'orderID';$T_7 = 1;
$value_1 = $_POST['wayOne'];
$value_2 = $_POST['wayTwo'];
if (!$value_1) {
    $T_1 = 'title';$T_2 = 'artist';$T_3 = 'description';$T_4 = 'yearOfWork';$T_5 = 'view';$T_6 = 'price';$T_7 = 1;
} else {
    for ($i = 0; $i < count($value_1); $i ++) {if ($value_1[$i] == 'wayOne_1') {$T_1 = 'title'; break;}}
    for ($i = 0; $i < count($value_1); $i ++) {if ($value_1[$i] == 'wayOne_2') {$T_2 = 'artist'; break;}}
    for ($i = 0; $i < count($value_1); $i ++) {if ($value_1[$i] == 'wayOne_3') {$T_3 = 'description'; break;}}
    for ($i = 0; $i < count($value_1); $i ++) {if ($value_1[$i] == 'wayOne_4') {$T_4 = 'yearOfWork'; break;}}
    for ($i = 0; $i < count($value_1); $i ++) {if ($value_1[$i] == 'wayOne_5') {$T_5 = 'view'; break;}}
    for ($i = 0; $i < count($value_1); $i ++) {if ($value_1[$i] == 'wayOne_6') {$T_6 = 'price'; break;}}
}

if (!$value_2) {
    $T_7 = 1;
} else {
    if ($value_2 == 'wayTwo_2') $T_7 = 2;
    if ($value_2 == 'wayTwo_3') $T_7 = 3;
    if ($value_2 == 'wayTwo_4') $T_7 = 4;
}

?>

    <script type="text/javascript">
        function showpage(url) {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState = 4) {
                    document.getElementById("result").innerHTML = xhr.responseText;
                }
            };

            xhr.open('get',url);
            xhr.send(null);
        }

        window.onload = function () {
            showpage('search.php?searchText=<?=$searchText ?>&T_1=<?=$T_1?>&T_2=<?=$T_2?>&T_3=<?=$T_3?>&T_4=<?=$T_4?>&T_5=<?=$T_5?>&T_6=<?=$T_6?>&T_7=<?=$T_7?>');
        }
    </script>

    <form class="container" action="searchResult.php" method="post">
        <table class='table table-bordered table-hover'>
            <tr>
                <td>搜索范围：</td>
                <td><input name="wayOne[]" type="checkbox" value="wayOne_1">标题</td>
                <td><input name="wayOne[]" type="checkbox" value="wayOne_2">作者</td>
                <td><input name="wayOne[]" type="checkbox" value="wayOne_3">简介</td>
                <td><input name="wayOne[]" type="checkbox" value="wayOne_4">年代</td>
                <td><input name="wayOne[]" type="checkbox" value="wayOne_5">热度</td>
                <td><input name="wayOne[]" type="checkbox" value="wayOne_6">价格</td>
            </tr>
            <tr>
                <td>排序方式：</td>
                <td><input name="wayTwo" type="radio" value="wayTwo_1">价格升序</td>
                <td><input name="wayTwo" type="radio" value="wayTwo_2">价格降序</td>
                <td><input name="wayTwo" type="radio" value="wayTwo_3">热度升序</td>
                <td><input name="wayTwo" type="radio" value="wayTwo_4">热度降序</td>
                <input type="hidden" name="search" value="<?= $searchText ?>">
                <td colspan="2"><button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button></td>
            </tr>
        </table>
    </form>

    <div class="container">
        <div id="result" class='row'></div>
    </div>

<?php
require_once 'footer.php';