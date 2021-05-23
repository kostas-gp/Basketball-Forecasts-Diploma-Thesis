<?php

session_start();
include ("connection.php");
if(!isset($_SESSION['email'])){

echo "<meta http-equiv='refresh' content='0; url=http://localhost/diplwmatiki_project/index.php' />";
//header("location: index.php");
}

?>

<html>

<head>

    <link rel="stylesheet" type="text/css" href="style1.css">  
    <meta charset="UTF-8">

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
<ul >
    <!--  <li><a href="index.html">Αρχικη</a></li>  -->
    <li><a style="color :white;" href="prognwseis.php">Προγνωσεις</a></li>
    <li><a style="color :white;" href="statistika.php">Στατιστικά</a></li>
    <li><a style="color :white;" href="epikoinwnia.php">Στοιχεία χρήστη</a></li>
</ul>

<p style="position: relative;  left: 700px; margin-top :15px;">
    
	<button onclick="window.location.href='logout.php'">Αποσύνδεση</button> 
           
</p>

<!-- pinakas gia ta koumpia -->	
<h1 style="size:6; color :black; face :verdana; "> "Ξεκινήστε την πρόβλεψη !!! " </h1>

<!-- forma gia na provlepsi to apotelesma dio omadwn -->
<table>
      
	 <form action="" ; method="post" ; align = "center" >
	   
	   <td> <font size = "3" color = "white" face = " verdana" > "Εισάγετε τα στοιχεία σας" </font>
	   </td>
 
	  <tr> 
		<td style="color :white;" > Team 1: </td>
		<td> <Input type = "text" name = "team1" id = "team1"> </td>
	  </tr>
    
      <tr> 
		<td style="color :white;" > Team 2: </td>
		<td> <Input type = "text" name = "team2" id = "team2"> </td>
	  </tr>
	  
	  <form action="">
	  
	  <tr>
	  <td style="color :white;"> Entos edras </td>
	  <td> <input type="radio" name="Entos edras" id="Entos edras"> </td>
	  </tr>
	  
      <tr>
	  <td style="color :white;" > Ektos edras </td>
	  <td> <input type="radio" name="Ektos edras" id="Ektos edras"> </td>
	  </tr>
	  
	  </form>

	  <td align ="center">
	   <input type="submit" value="Prediction" >
	  </td>
     
	 </form>	
</table>

</div>
</body>

</html>