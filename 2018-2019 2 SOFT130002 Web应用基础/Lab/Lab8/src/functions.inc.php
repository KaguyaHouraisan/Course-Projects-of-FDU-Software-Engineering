<?php
    function outputOrderRow($file, $title, $quantity, $price) {
        echo "<tr>";
        echo "<td><img src=\"images/books/tinysquare/$file\"></td>
                      <td class=\"mdl-data-table__cell--non-numeric\">$title</td>
                      <td>" . $quantity . "</td>
                      <td>$" . sprintf("%.2f", $price) . "</td>
                      <td>$" . sprintf("%.2f", $quantity * $price) . "</td>";
        echo "</tr>";
    }
?>