<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['name']) && !empty($_POST['price'])){
        $name = $_POST['name'];
        $price = $_POST['price'];

        $sql = "insert into product (name, price) values (?, ?);";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sd', $name, $price);
        $stmt->execute();
        echo ("Request sucessfull!");
    }else{
        echo ("Request failed: empty value");
    }
    $conn->close();
}


?>