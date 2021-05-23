<?php

session_start();
include "connection.php";

if ($_SERVER['REQUEST_METHOD'] == "POST" ) 
{
    $email = $_POST['email'];  
    $userName = $_POST['userName'];
    $Password = $_POST['Password'];
    $repassword =$_POST['repassword'];
	$photo =$_POST['photo'];
    $password_matches = 0;
    $password_is_strong = 0;
	
	//elegxei an iparxei idi stin vasi to mail
	
	$query = mysqli_query($conn,"SELECT email FROM Users WHERE email='$email'");

    if (mysqli_num_rows($query) != 0)
    {
       echo "Το συγκεκριμένο email υπάρχει ήδη. Προπαθήστετ με κάποιο άλλο.";
	   echo "<meta http-equiv='refresh' content='1; url=http://localhost/diplwmatiki_project/index.php' />";
	   exit();
    }
	
    
	//elegxei an exoun simplirwthei oles oi times	
    if ((empty($email) || empty($userName) || empty($Password) || empty($repassword) )) 
	{
        echo('Πρέπει να συμπληρώσετε τα υποχρεωτικά πεδία (με τον αστερίσκο *)');
        echo "<meta http-equiv='refresh' content='1; url=http://localhost/diplwmatiki_project/index.php' />";
		exit();
    }
	
	//elegxei an to password einai idio me allo	
    if ($Password == $repassword && strlen($Password) > 5 )
    {  
        if  (preg_match('/[^a-z0-9 ]/', $Password))
        {
        	$password_is_strong = 1;
            $password_matches = 1;
            $Password=($Password);
        }
        
	}
    else 
    {
           echo('Πρέπει να βάλετε στα πεδία password και confirm password τον ίδιο κωδικό<br>');
		   echo('');
           echo('Ο κωδικός σας πρέπει να έχει τουλάχιστον 5 χαρακτήρες-->Α/α/2/$');
		   echo('');
           echo "<meta http-equiv='refresh' content='1; url=http://localhost/diplwmatiki_project/index.php' />";           
    }
    
	// an ola ta parapanw einai ok proxwraei sto register  
    if ($password_matches == 1 && $password_is_strong == 1 )
	{
		
    $conn->autocommit(FALSE);
    
    $conn->query ( "insert into users 
                            (
                                email,
                                userName,
                                Password,
                                repassword,
                                photo
                            ) 
                            Values
                            (
                                '$email',
                                '$userName',
                                '$Password',
                                '$repassword',
								'$photo'
                            )"
				   );

   $result = $conn->commit();

    if ($result) 
	    {
         echo('Τα στοιχεία σας καταχωρήθηκαν με επιτυχία.');
		 echo "<meta http-equiv='refresh' content='1; url=http://localhost/diplwmatiki_project/index.php' />";
		 exit();
		}
		
        else 
		{
         echo('Τα στοιχεία δεν καταχωρήθηκαν λόγω προβλήματος στην βάση του συστήματος.');
		 echo "<meta http-equiv='refresh' content='1; url=http://localhost/diplwmatiki_project/index.php' />";
         exit();
		}
    }
}
$conn->close();
?>


