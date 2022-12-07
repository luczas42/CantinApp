<?php
header('Content-Type: application/json charset=utf-8');

$response = array();
$response["error"] = true;

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'dbConnection.php';

    $conn = new mysqli($HostName, $HostUser, 
    $HostPass, $DatabaseName); 

    mysqli_set_charset($conn, "utf8");

    if($conn->connect_error){
        die("Connection failed: ". $conn->connect_error);
    }

    $name = "'".$_POST['product_name']."'";

    $sql = "SELECT * FROM product WHERE name = $name";

    $result = $conn->query($sql);

    if($result->num_rows>0){

        $register = mysqli_fetch_array($result);

        $response["lines"] = $result->num_rows;
        $response["error"] = false;
        $response["name"] = $register['name'];
        $response["price"] = $register['price'];
    }else{
        //teste
    }

    $conn->close();
}

echo json_encode($response);

?>