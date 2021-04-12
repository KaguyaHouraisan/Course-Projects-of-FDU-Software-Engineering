<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');
mysqli_query($con,"set names utf8");

//足迹
$sql = "SELECT * FROM foot WHERE name = 'index'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'index')";
mysqli_query($con,$sql);

require_once 'header.php';

if(Session::exists('home')) {
    echo '<div class="alert alert-success" role="alert">' . Session::flash('home') . '</div><br>';
}
?>
    <link rel="stylesheet" href="css/index.css">
    <div class="container">
        <h3>Hottest Products</h3>
        <!-- 3d旋转 -->
        <div class="stage">
            <div class="box">
                <?php
                $db = DB::getInstance();
                $items = $db->orderAction('SELECT * ','artworks where orderID is null',  'view DESC', 9);
                $count = $items->count();
                $items = $items->results();
                for($y = 0; $y < 9; $y++) {
                ?>
                    <div class="img-item thumbnail">
                        <img src="resources/img/<?=$items[$y]->imageFileName ?>"  class="img-item-0 img-responsive">
                        <div class="caption">
                            <b><a href="details.php?id=<?=$items[$y]->artworkID ?>"><?=$items[$y]->title ?></a></b>
                            <br>Price: $<?=$items[$y]->price ?>
                            <br>Popularity Index: <?=$items[$y]->view ?>
                            <p style="font-size: 12px !important;"><?=$items[$y]->description ?></p>
                        </div>
                    </div>
                    <?php
                }
                ?>
            </div>
        </div>

        <h3>New Products</h3>
        <?php
        $db = DB::getInstance();
        $items = $db->orderAction('SELECT *', 'artworks where orderID is null', 'timeReleased DESC', 3);
        $count = $items->count();
        $items = $items->results();
        for($x = 0; $x < $count; $x ++) {
        ?>
            <div class="row">
                <?php
                $y=0;
                for($y = 0; $y < 3; $y++) {
                ?>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                    <?php
                    if($x>=$count) {
                        echo '';
                    } else {
                        ?>
                        <div id="over" class="thumbnail">
                            <!--<div class="image">-->
                                <img src="resources/img/<?=$items[$x]->imageFileName ?>" class="img-responsive" style="height: 400px">
                            <!--</div>-->
                            <div class="caption">
                                <b><a href="details.php?id=<?=$items[$x]->artworkID ?>"><?=$items[$x]->title ?></a></b>
                                <br>Price: $<?=$items[$x]->price ?>
                                <br>Update Time: <?=$items[$x]->timeReleased ?>
                                <p><?=$items[$x]->description ?></p>
                            </div>
                        </div>
                        <?php
                    }
                    $x++;
                    ?>
                    </div>
                    <?php
                }
                ?>
            </div>
            <?php
        }
        ?>
    </div>
    <script src="js/index.js"></script>
<?php

require_once 'footer.php';