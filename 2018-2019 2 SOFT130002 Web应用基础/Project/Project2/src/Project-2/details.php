<?php

$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'details'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'details')";
mysqli_query($con,$sql);

require_once 'header.php';

if(Session::exists('home')) {
    echo '<div class="alert alert-success" role="alert">' . Session::flash('home') . '</div><br>';
}

$db = DB::getInstance();
if(Input::exists('get')) {
    $id = Input::get('id');
    $result = $db->get('artworks', array('artworkID', '=', $id));
    $item = $result->results();
    $count = $result->count();
    //浏览量+1
    $db->updatePopIndex($id, array('artworkID' => $item[0]->view));
    ?>
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-md-3 col-lg-5">
                <img src="resources/img/<?=$item[0]->imageFileName ?>" width="100%" height="550px">
            </div>

            <div class="col-sm-12 col-md-8 col-lg-7">
                <h1 style="color: red"><?=$item[0]->title ?></h1>
                <div class="row">
                    <div class="col-sm-9">
                        <p><b>Artist: </b><a href="searchResult.php?searchText=<?=$item[0]->artist ?>"><?=$item[0]->artist ?></a></p>
                        <p><b>Price: $</b><?=$item[0]->price ?></p>
                        <p><b>Genre: </b><?=$item[0]->genre ?></p>
                        <p><b>Year: </b><?=$item[0]->yearOfWork ?></p>
                        <p><b>Popularity Index: </b><?=$item[0]->view ?></p>
                        <p><b>Width: </b><?=$item[0]->width ?> <b>cm</b></p>
                        <p><b>Height: </b><?=$item[0]->height ?> <b>cm</b></p>
                        <p><b>Seller: </b>
                            <?php
                            $temp = $item[0]->ownerID;
                            $res = $db->get('users', array('id', '=', $temp));
                            $userIn = $res->results();
                            ?>
                            <?=$userIn[0]->username ?>
                        </p>
                        <p><b>Is it sold ? </b>
                            <?php
                            $sold = $item[0]->orderID;
                            if ($sold > 0) {
                                echo 'Yes';
                            } else if ($sold = 'NULL') {
                                echo 'No';
                            }
                            ?>
                        </p>
                        <p><?=$item[0]->description ?></p>

                        <?php
                        if($user->isLoggedIn()) {
                            if ($sold > 0) {
                                ?>
                                <div class="btn-buy">
                                    <button class="btn btn-primary btn-block" disabled>已售出</button>
                                </div>
                                <?php
                            } else {
                                $sql = "SELECT * FROM carts WHERE userID = {$user->data()->id} AND artworkID = {$item[0]->artworkID}";
                                $re = $con->query($sql)->fetch_assoc();
                                if ($re) {
                                    ?>
                                    <div class="btn-buy">
                                        <button class="btn btn-primary btn-block" disabled>已加入购物车！</button>
                                    </div>
                                    <?php
                                } else {
                                    ?>
                                    <div class="btn-buy">
                                        <form action="insertCart.php" method="post">
                                            <input type="hidden" name="artworkID" value="<?= $item[0]->artworkID ?>">
                                            <input type="hidden" name="userID" value="<?= $user->data()->id ?>">
                                            <button type="submit" class="btn btn-primary btn-block">加入购物车</button>
                                        </form>
                                    </div>
                                    <?php
                                }
                            }
                        } else {
                            ?>
                            <div class="btn-buy">
                                <a href="login.php"><button class="btn btn-primary btn-block">请先登录再加入购物车</button></a>
                            </div>
                            <?php
                        }
                        ?>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <?php
}

if(Input::exists()) {
    $GLOBALS['cart'] = Input::get('id');
}

require_once 'footer.php';