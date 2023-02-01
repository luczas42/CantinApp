<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['id']) 
    && !empty($_POST['name']) 
    && !empty($_POST['class'])
    && !empty($_POST['day'])
    && !empty($_POST['period'])){

        $id = $_POST['id'];
        $name = $_POST['name'];
        $class = $_POST['class'];
        $day = $_POST['day'];
        $period = $_POST['period'];

        $sql = "UPDATE scale s, employee e, turn t 
        SET e.name = ?, e.class = ?, t.day = ?, t.period = ? 
        WHERE id = ?;";

        $stmt = $conn->prepare($sql);
        $stmt->bind_param('isssi', $id, $name, $class, $day, $period);
        $stmt->execute(); 
    }else{
    }
    $conn->close();
}


?>