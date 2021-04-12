<?php
//Fill this place
//****** Hint ******
//connect database and fetch data here
$conn = mysqli_connect('localhost', 'phoenix', '1049776188', 'travel');
if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Lab11</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/captions.css" />
    <link rel="stylesheet" href="css/bootstrap-theme.css" />
</head>

<body>
    <?php include 'header.inc.php'; ?>
    <!-- Page Content -->
    <main class="container">
        <div class="panel panel-default">
            <div class="panel-heading">Filters</div>
            <div class="panel-body">
                <form action="Lab11.php" method="get" class="form-horizontal">
                    <div class="form-inline">
                        <select name="continent" class="form-control">
                            <option value="0">Select Continent</option>
                            <?php
                            //Fill this place
                            //****** Hint ******
                            //display the list of continents
                            $continents = $conn->query("SELECT ContinentName, ContinentCode FROM continents");
                            while($row = $continents->fetch_assoc()) {
                                echo '<option value=' . $row['ContinentCode'] . '>' . $row['ContinentName'] . '</option>';
                            }
                            ?>
                        </select>

                        <select name="country" class="form-control">
                            <option value="0">Select Country</option>
                            <?php
                            //Fill this place
                            //****** Hint ******
                            /* display list of countries */
                            $countries = $conn->query("SELECT CountryName,ISO FROM countries");
                            while($row = $countries->fetch_assoc()) {
                                echo '<option value=' . $row['ISO'] . '>' . $row['CountryName'] . '</option>';
                            }
                            ?>
                        </select>
                        <input type="text"  placeholder="Search title" class="form-control" name=title>
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </div>
                </form>
            </div>
        </div>

        <ul class="caption-style-2">
            <?php
            //Fill this place
            //****** Hint ******
            /* use while loop to display images that meet requirements ... sample below ... replace ???? with field data*/
            $result = $conn->query("SELECT * FROM imagedetails");
            while($row = $result->fetch_assoc()) {
                findBy($row);
            };
            $conn->close();

            function findBy($row) {
                if (!array_key_exists("continent", $_GET) or (($row['ContinentCode']== $_GET['continent'] or $_GET['continent']==="0") and ($row['CountryCodeISO']==$_GET['country'] or $_GET['country']==="0") and ($_GET['title']=="" or $_GET['title']==$row['Title']))){
                    echo "
                        <li>
                            <a href=\"detail.php?id=".$row['ImageID']."\" class=\"img-responsive\">
                                <img src=\"images/square-medium/" . $row['Path'] ."\" alt=\"" . $row['Title'] . "\">
                                <div class=\"caption\">
                                    <div class=\"blur\">
                                        <div class=\"caption-text\">
                                            <p>" . $row['Description'] . "</p>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li> ";
                }
            }
            ?>
       </ul>
    </main>
    
    <footer>
        <div class="container-fluid">
                    <div class="row final">
                <p>Copyright &copy; 2017 Creative Commons ShareAlike</p>
                <p><a href="#">Home</a> / <a href="#">About</a> / <a href="#">Contact</a> / <a href="#">Browse</a></p>
            </div>            
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</body>
</html>