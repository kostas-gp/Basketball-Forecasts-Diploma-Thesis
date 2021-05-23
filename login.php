<html>
<head>
<?php
    session_start();
	include ("connection.php");
?>

<?php

 
	$email=$_POST['email'];
	$Password=$_POST['Password'];
	
	$sql="select * from users where email = '$email' && Password='$Password'";
	$result = $conn->query($sql) or die(mysql_error());
	if ($result->num_rows == 0)
	{   

	  echo "<br>Wrong mail or password <meta http-equiv='refresh' content='3; url=http://localhost/diplwmatiki_project/index.php' />";
	
	}
	else
	{
		// Set session variables
		$_SESSION['email'] = "$email";
		
		//echo "Session variables are set.";
        echo"WELCOME TO OUR SITE<br>";
        echo"<br>LOGGED IN AS--><br><br><br>";
		echo $_SESSION["email"];
		
		echo "<meta http-equiv='refresh' content='3; url=http://localhost/diplwmatiki_project/prognwseis.php' />";
		  
	}

?>
	
</head>
<body>

</body>
</html>