<?php
include 'dbConnectionTest.php';
        
    $conn = new mysqli($HostName, $HostUser, 
    $HostPass, $DatabaseName); 

    mysqli_set_charset($conn, "utf8");

    if($conn->connect_error){
        die("Connection failed: ". $conn->connect_error);
    }
?>