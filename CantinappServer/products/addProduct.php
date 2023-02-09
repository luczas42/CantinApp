<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['name']) && !empty($_POST['price']) && !empty($_POST['byte_array'])){
        $name = $_POST['name'];
        $price = $_POST['price'];
        $byte_array = $_POST['byte_array'];

        $sql = "INSERT INTO product (name, price, image) VALUES (?, ?, ?);";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sdb', $name, $price, $byte_array);
        $stmt->execute();
    }
    $conn->close();
}


?>