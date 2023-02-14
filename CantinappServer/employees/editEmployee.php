<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['id']) && !empty($_POST['name']) && !empty($_POST['class'])){

        $id = $_POST['id'];
        $name = $_POST['name'];
        $class = $_POST['class'];

        $sql = "UPDATE employee SET name = ?, class = ? WHERE id = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('ssi', $name, $class, $id);
        $stmt->execute(); 
    }
    $conn->close();
}


?>