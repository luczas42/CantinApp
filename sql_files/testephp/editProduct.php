<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['id']) && !empty($_POST['name']) && !empty($_POST['price'])){

        $id = $_POST['id'];
        $name = $_POST['name'];
        $price = $_POST['price'];

        $sql = "UPDATE product SET name = ?, price = ? WHERE id = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sdi', $name, $price, $id);
        $stmt->execute(); 
    }
    $conn->close();
}


?>