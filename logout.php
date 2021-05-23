<?php

     session_start();
//session_destroy();

     echo"SEE YOU AGAIN ";
     echo $_SESSION["email"];
	 unset($_SESSION['email']);

     echo "<meta http-equiv='refresh' content='1; url=http://localhost/diplwmatiki_project/index.php' />";
?>