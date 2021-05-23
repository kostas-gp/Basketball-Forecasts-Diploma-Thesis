<?php

session_start();
include ("connection.php");
if(!isset($_SESSION['email'])){

echo "<meta http-equiv='refresh' content='0; url=http://localhost/diplwmatiki_project/index.php' />";
//header("location: index.php");
}

?>

<?php
if (isset($_GET['url'])) {
     $output = shell_exec('java -jar Java/Crawler.jar ' . '"' . $_GET['url'] .  '"'); //<- ithele " " giauto e
}   
?>

<html>
<head>

    <link rel="stylesheet" type="text/css" href="style1.css">  
    <meta charset="UTF-8">

</head>
<body>

<div id="page-wrap">
    
<!-- titlos-->
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


 

 <?php
  
   $servername = "localhost";
   $username = "root";
   $password = "";
   $database = "users";

    // Create connection
    $conn = new mysqli($servername, $username, $password,$database);
    	
    // store the user id into session
	$email = $_SESSION["email"]; 

	
	$query = "SELECT * FROM users WHERE email='$email'";
	$conn=  mysqli_query($conn, $query);
	//isset($_SESSION['email']);
	//$conn=  mysqli_query($conn, "select * from users");
	
            while($row=  mysqli_fetch_array($conn)){
			 	 
                 ?>

<table>
  <td>
  </td>
   <td>
  </td>
   <td>
  </td>
  
  <tr>
      <td style="color :white;">Name: </td>
	  <td><?php echo $row['username']; ?></td>
  </tr>
  
  <tr>
      <td style="color :white;">Email: </td>
	  <td><?php echo $row['email']; ?></td>
  </tr> 
 <td>
  </td>
   <td>
  </td>
   <td>
  </td>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<form  action="epikoinwnia.php">

<br> </br>
<tr>
            <td style="color :white;"> URL Σελίδας:</td>
			<td >
            <input type="text" name="url" placeholder="http://www.euroleague.net/main/results/showgame?..." required>
            </tr>
			<td/>
			
            <input type="submit" value="Get data!">
        </form> 

        <p style="color :white;" >
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

   <?php
             }
             ?>
			 
					 

</table>

</div>  
</body>
</html>