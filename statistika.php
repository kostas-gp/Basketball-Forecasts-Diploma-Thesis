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


<script type="text/javascript">

function createRequestObject() {
    var obj;
    var browser = navigator.appName;
    if (browser == "Microsoft Internet Explorer") {
        obj = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        obj = new XMLHttpRequest();
    }
    return obj;
}

function sendReq(req) {   
    var http = createRequestObject();
    http.open('get', req);
    http.onreadystatechange = handleResponse;
    http.send(null);
}

function handleResponse() {    
    if (http.readyState == 4) {
        var response = http.responseText;
        document.getElementById('setADivWithAnIDWhereYouWantIt').innerHTML=response;
    }
}
sendReq('yourpage');

</script> 

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
	 
<!-- emfanizetai i selida pou thelw na paei -->
	 
<iframe  width="100%" height="100%" src=http://euroleague.sport24.gr/"/>
   
</div>   
</body>
</html>