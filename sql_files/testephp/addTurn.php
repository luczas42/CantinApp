<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['day']) && !empty($_POST['period']) 
    && !empty($_POST['class'])){

        $day = $_POST['day'];
        $period = $_POST['period'];
        $class = $_POST['class'];

        $sql = "INSERT INTO turn (day, period, class) VALUES (?, ?, ?);";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sis', $day, $period, $class);
        $stmt->execute();
    }
    $conn->close();
}


?>