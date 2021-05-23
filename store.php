<?php
if (isset($_GET['url'])) {
     $output = shell_exec('java -jar Java/Crawler.jar ' . '"' . $_GET['url'] .  '"'); //<- ithele " " giauto e
}   
?>

<!DOCTYPE html>
<html>
    <head>
	    <link rel="stylesheet" type="text/css" href="style1.css">  
        <meta charset="utf-8">
        <title>Euroleague Crawler</title>
    </head>
    <body>
	
	<div id="page-wrap">
	
<div id="header"  >
    <h>Τμήμα Μηχανικών Πληροφοριακών και Επικοινωνιακών Συστημάτων</h>
	
	<br>
	<br>
    <h>Σελίδα για προγνώσεις αγώνων Μπάσκετ</h>
</div>
 
<!-- navigation bar --> 
<ul>
   <li><a style="color :white;" href="prognwseis.php">Προγνωσεις</a></li>
    <li><a style="color :white;" href="statistika.php">Στατιστικά</a></li>
    <li><a style="color :white;" href="epikoinwnia.php">Στοιχεία χρήστη</a></li>
</ul>

<p style="position: relative;  left: 700px; margin-top :15px;">
    
	<button onclick="window.location.href='logout.php'">Αποσύνδεση</button> 
           
</p>


        <form action="store.php">
            URL Σελίδας:<br>
            <input type="text" name="url" placeholder="http://www.euroleague.net/main/results/showgame?..." required>
            <br><br>
            <input type="submit" value="Get data!">
        </form> 

        <p>
            <?php
            if (isset($_GET['url'])) {
                if(strlen ($output)===6){
                    echo "Τα δεδομένα του παιχνιδιου αποθηκεύτηκαν στην βάση!";
                }else{
                    echo "Προέκυψε κάποιο σφάλμα!";
                }
            }
            ?>
        </p>
    </body>
</html>