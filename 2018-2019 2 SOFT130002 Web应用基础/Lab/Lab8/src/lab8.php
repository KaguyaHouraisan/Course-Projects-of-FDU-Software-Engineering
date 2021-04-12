<?php include 'functions.inc.php'; ?>
<?php 

include 'data.inc.php'; 

$shippingThreshold = 10000;
$shippingFlatAmount = 200;

// 计算 subtotal, shipping, grand total
// grand total = subtotal + shipping
// 如果subtotal值超过阈值，则为100；反之则为$shippingFlatAmount
$subtotal = $price1 * $quantity1 + $price2 * $quantity2 + $price3 * $quantity3 + $price4 * $quantity4;
$shipping = $subtotal > $shippingThreshold ? 100 : $shippingFlatAmount;
$grandTotal = $subtotal + $shipping;

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Lab 08 - PHP</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.blue_grey-orange.min.css">
    
    <link rel="stylesheet" href="css/styles.css">
    <script defer src="https://code.getmdl.io/1.1.3/material.min.js"></script>
</head>

<body>
    
<!-- You should decide where to add the `header.inc.php` and `left.inc.php` -->
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer
            mdl-layout--fixed-header">

    <?php include './left.inc.php'?>
    <?php include './header.inc.php'?>

    <main class="mdl-layout__content mdl-color--grey-50">
    <header class="mdl-color--blue-grey-200">
        <h4>Order Summaries</h4>
        <p>Examine your customer orders</p>
    </header>

    <section class="page-content">
        <div class="mdl-grid">

            <!-- mdl-cell + mdl-card -->
            <div class="mdl-cell mdl-cell--3-col card-lesson mdl-card  mdl-shadow--2dp">
                <div class="mdl-card__title mdl-color--deep-purple mdl-color-text--white">
                    <h2 class="mdl-card__title-text">My Orders</h2>
                </div>

                <div class="mdl-card__supporting-text">
                    <ul class="mdl-list">
                        <?php
                            for ($i=500; $i<550; $i+=10) {
                                echo '<li><a href="#">Order #' . $i . '</a></li>';
                            }
                        ?>
                    </ul>
                </div>
            </div>  <!-- / mdl-cell + mdl-card -->

            <!-- mdl-cell + mdl-card -->
            <div class="mdl-cell mdl-cell--9-col card-lesson mdl-card  mdl-shadow--2dp">
                <div class="mdl-card__title mdl-color--orange">
                    <h2 class="mdl-card__title-text">Selected Order: #520</h2>
                </div>

                <div class="mdl-card__supporting-text">
                    <table class="mdl-data-table  mdl-shadow--2dp">
                        <caption>Customer: <strong>Mount Royal University</strong></caption>

                        <thead>
                            <tr>
                                <th>Cover</th>
                                <th class="mdl-data-table__cell--non-numeric">Title</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Amount</th>
                            </tr>
                        </thead>

                        <tfoot>
                            <tr class="totals">
                                <td colspan="4">Subtotal</td>
                                <td>$<?php echo number_format($subtotal,2); ?></td>
                            </tr>

                            <tr class="totals">
                                <td colspan="4">Shipping</td>
                                <td>$<?php echo number_format($shipping,2); ?></td>
                            </tr>

                            <tr class="grandtotals">
                                <td colspan="4">Grand Total</td>
                                <td>$<?php echo number_format($grandTotal,2); ?></td>
                            </tr>
                        </tfoot>

                        <tbody>
                            <?php
                                // Here you need to complete the function outputOrderRow() in functions.inc.php
                                outputOrderRow($file1, $title1, $quantity1, $price1);
                                outputOrderRow($file2, $title2, $quantity2, $price2);
                                outputOrderRow($file3, $title3, $quantity3, $price3);
                                outputOrderRow($file4, $title4, $quantity4, $price4);
                            ?>
                        </tbody>
                    </table>
                </div>
            </div>  <!-- / mdl-cell + mdl-card -->
        </div>  <!-- / mdl-grid -->
    </section>
    </main>
</div>

</body>
</html>