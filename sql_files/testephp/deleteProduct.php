<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['id'])){
        $id = $_POST['id'];

        $sql = "delete from product where id = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('i', $id);
        $stmt->execute(); 
        //echo ("Request sucessfull!");
    }else{
        //echo ("Request failed: empty value");
    }
    $conn->close();
}


?>