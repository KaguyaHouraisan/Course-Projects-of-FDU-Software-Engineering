<?php
error_reporting(E_ALL ^ E_NOTICE);
$values = $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
$con = mysqli_connect('localhost','phoenix','1049776188','phoenix');

//足迹
$sql = "SELECT * FROM foot WHERE name = 'change'";
$row = $con->query($sql)->fetch_assoc();
$temp = $row["id"];

$sql = "DELETE FROM foot WHERE id >= {$temp}";
$con->query($sql);

$sql = "INSERT INTO foot VALUES (null , '{$values}' , 'change')";
mysqli_query($con,$sql);

require_once 'header.php';
if(!$user->isLoggedIn()) {
    Redirect::to('login.php');
}

if ($_GET['artworkID']) {
    $sql = "SELECT * FROM artworks WHERE artworkID = {$_GET['artworkID']}";
    $row = $con->query($sql)->fetch_assoc();
}
?>

<script type="text/javascript">
    function showPreview(source) {
        var file = source.files[0];
        if(window.FileReader) {
            var fr = new FileReader();
            console.log(fr);
            var portrait = document.getElementById('portrait');
            fr.onloadend = function(e) {
                portrait.src = e.target.result;
            };
            fr.readAsDataURL(file);
            portrait.style.display = 'block';
        }
    }
</script>

<form enctype="multipart/form-data"  class="row" action="changeResult.php" method="post">
    <div class="col-md-4">
        <div class="left-box">
            <div class="form-group-lg">
                <div class="login-input">
                    <?php
                    if ($_GET['artworkID']) {
                        ?>
                        <label style="height: 200px; width: 150px; border: 3px solid rgb(150,150,150)">
                            <img src="resources/img/<?= $row['imageFileName'] ?>" class="img-responsive" style="width: 100%; height: 100%">
                        </label>
                        <?php
                    }
                    ?>

                    <label style="height: 200px; width: 150px; border: 3px solid rgb(150,150,150)">
                        <img src="resources/BG2.jpg" id="portrait" class="img-responsive" style="width: 100%; height: 100%">
                    </label>
                </div>

                <div class="login-input">
                    <input class="btn btn-success login-bt" name="upFile" type="file" onchange="showPreview(this)">
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/common.css">

        <div class="middle-box">
            <div class="form-group-lg login-box">
                <div class="login-input">
                    <label class="username">标题:<input name="title" type="text" value="<?php echo escape($row['title']); ?>" required></label><br>
                </div>

                <div class="login-input">
                    <label class="username">作者:<input name="artist" type="text" value="<?php echo escape($row['artist']); ?>" required></label><br>
                </div>

                <div class="login-input">
                    <label class="username">年代（整数）:<input name="year" type="text" value="<?php echo escape($row['yearOfWork']); ?>" pattern="^-?[0-9]\d*$"  required></label><br>
                </div>

                <div class="login-input">
                    <label class="username">风格:<input name="style" type="text" value="<?php echo escape($row['genre']); ?>" required></label><br>
                </div>

                <div class="login-input">
                    <label class="username">宽度（正数）:<input name="width" type="text" value="<?php echo escape($row['width']); ?>" pattern="^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$" required></label><br>
                </div>

                <div class="login-input">
                    <label class="username">高度（正数）:<input name="height" type="text" value="<?php echo escape($row['height']); ?>" pattern="^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$" required></label><br>
                </div>

                <div class="login-input">
                    <label class="username">价格（正整数）:<input name="price" type="text" value="<?php echo escape($row['price']); ?>" pattern="^[1-9]\d*$" required></label><br>
                </div>

                <div class="login-input" style="visibility: hidden">
                    <?php
                    if ($_GET['artworkID']) {
                        ?>
                        <label class="username">价格:<input type="text" name="type" value="<?php echo escape($_GET['artworkID']) ?>"></label><br>
                        <?php
                    } else {
                        ?>
                        <label class="username">价格:<input type="text" name="type" value="0"></label><br>
                        <?php
                    }
                    ?>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="right-box">
            <div class="form-group-lg">
                <div class="login-input">
                    <label class="username">
                        <textarea style="height: 450px; width: 300px" name="description" required>
                            <?=$row['description'] ?>
                        </textarea>
                    </label>
                </div>

                <div class="login-input">
                    <button class="btn btn-success login-bt" type="submit" name="submit">
                        <?php
                        if ($_GET['artworkID']) {
                            echo "修改";
                        } else {
                            echo "上传";
                        }
                        ?>
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
