<?php
header("Content-type:text/html; charset=utf-8");
$con = mysqli_connect("localhost","phoenix","1049776188","phoenix");

$searchAddSql = '';
$searchText = $_GET["searchText"];
$searchAddSql = $searchAddSql." 
and (" . $_GET['T_1'] . " like '%".$searchText."%' 
or " . $_GET['T_2'] . "  like '%".$searchText."%'
or " . $_GET['T_3'] . "  like '%".$searchText."%'
or " . $_GET['T_4'] . "  like '%".$searchText."%'
or " . $_GET['T_5'] . "  like '%".$searchText."%'
or " . $_GET['T_6'] . "  like '%".$searchText."%')";

if ($_GET['T_7'] == 1) $sql = "SELECT * from artworks where orderID is null " . $searchAddSql . " order by price";
if ($_GET['T_7'] == 2) $sql = "SELECT * from artworks where orderID is null " . $searchAddSql . " order by price DESC";
if ($_GET['T_7'] == 3) $sql = "SELECT * from artworks where orderID is null " . $searchAddSql . " order by view";
if ($_GET['T_7'] == 4) $sql = "SELECT * from artworks where orderID is null " . $searchAddSql . " order by view DESC";
$qry = $con->query($sql);

$total = mysqli_num_rows($qry);
$page = pageDivide($total,10);

if ($_GET['T_7'] == 1) $sql = "SELECT * from artworks where orderID is null " . $searchAddSql . " order by price limit $sqlfirst,$shownu";
if ($_GET['T_7'] == 2) $sql = "SELECT * from artworks where orderID is null " . $searchAddSql . " order by price DESC limit $sqlfirst,$shownu";
if ($_GET['T_7'] == 3) $sql = "SELECT * from artworks where orderID is null " . $searchAddSql . " order by view limit $sqlfirst,$shownu";
if ($_GET['T_7'] == 4) $sql = "SELECT * from artworks where orderID is null " . $searchAddSql . " order by view DESC limit $sqlfirst,$shownu";
$result = $con->query($sql);

function pageDivide($total,$shownu=20,$url=''){
    global $page,$sqlfirst,$pagecon,$_SERVER;
    $GLOBALS["shownu"]=$shownu;

    if(isset($_GET['page'])){
        $page=$_GET['page'];
    }else $page=1;

    #如果$url使用默认,即空值,则赋值为本页URL
    if(!$url){
        $url = $_SERVER["REQUEST_URI"];
    }

    #URL分析
    $parse_url=parse_url($url);
    @$url_query=$parse_url["query"]; //取出在问号?之后内容
    if($url_query){
        $url_query=preg_replace("/(&?)(page=$page)/","",$url_query);
        $url = str_replace($parse_url["query"],$url_query,$url);
        if($url_query){
            $url .= "&page";
        }else $url .= "page";
    }else $url .= "?page";

    #页码计算
    $lastpg=ceil($total/$shownu); //最后页,总页数
    $page=min($lastpg,$page);
    $prepg=$page-1; //上一页
    $nextpg=($page==$lastpg ? 0 : $page+1); //下一页
    $sqlfirst=($page-1)*$shownu;

    #开始分页导航内容
    $pagecon = "显示第 ".($total?($sqlfirst+1):0)."-".min($sqlfirst+$shownu,$total)." 条记录，共 <B>$total</B> 条记录";
    if($lastpg<=1) return false; //如果只有一页则跳出

    if($page!=1) $pagecon .=" <a href='javascript:showpage(\"$url=1\")'>首页</a> "; else $pagecon .=" 首页 ";
    if($prepg) $pagecon .=" <a href= 'javascript:showpage(\"$url=$prepg\")'>前页</a> "; else $pagecon .=" 前页 ";
    if($nextpg) $pagecon .=" <a href= 'javascript:showpage(\"$url=$nextpg\")'>后页</a> "; else $pagecon .=" 后页 ";
    if($page!=$lastpg) $pagecon.=" <a href= 'javascript:showpage(\"$url=$lastpg\")'>尾页</a> "; else $pagecon .=" 尾页 ";

    #下拉跳转列表,循环列出所有页码
    $pagecon .="　到第 <select name='topage' size='1' onchange='showpage(\"$url=\"+this.value)'>\n";
    for($i=1;$i<=$lastpg;$i++){
        if($i==$page) $pagecon .="<option value='$i' selected>$i</option>\n";
        else $pagecon .="<option value='$i'>$i</option>\n";
    }
    $pagecon .="</select> 页，共 $lastpg 页";

    return $page;
}

echo "<table class='table table-bordered table-hover'><tr><td>缩略图</td><td>标题</td><td>作者</td><td>年代</td><td>风格</td><td>价格</td><td>热度</td><td>详情</td></tr>";

$num = ($page - 1) * 10;

if ($result) {
    while($goods = mysqli_fetch_assoc($result)){
        echo "<tr>";
        echo "<td style='width: 15%'><img src='resources/img/$goods[imageFileName]' class='img-responsive' style='height: 15%'></td>";
        echo "<td>$goods[title]</td>";
        echo "<td>$goods[artist]</td>";
        echo "<td>$goods[yearOfWork]</td>";
        echo "<td>$goods[genre]</td>";
        echo "<td>$goods[price]</td>";
        echo "<td>$goods[view]</td>";
        echo "<td><a href='details.php?id=$goods[artworkID]'><button class='btn btn-primary btn-block'>Learn More</button></a></td>";
        echo "</tr>";
    }
}

echo "<tr><td colspan='8'>" . $pagecon . "</td></tr>";
echo "</table>";