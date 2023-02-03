<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['id'])){
        $id = $_POST['id'];

        $sql = "delete from turn where id = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('i', $id);
        $stmt->execute(); 
    }else{
    }
    $conn->close();
}


?>