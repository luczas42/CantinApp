<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['id']) 
    && !empty($_POST['day']) 
    && !empty($_POST['period']) 
    && !empty($_POST['class'])){

        $id = $_POST['id'];
        $day = $_POST['day'];
        $period = $_POST['period'];
        $class = $_POST['class'];

        $sql = "UPDATE turn SET day = ?, period = ?, class = ? WHERE id = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sisi', $day, $period, $class, $id);
        $stmt->execute(); 
    }
    $conn->close();
}


?>