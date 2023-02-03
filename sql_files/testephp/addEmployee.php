<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['name']) && !empty($_POST['class'])){

        $name = $_POST['name'];
        $class = $_POST['class'];

        $sql = "INSERT INTO employee (name, class)
        VALUES (?, ?)";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('ss', $name, $class);
        $stmt->execute();
    }
    $conn->close();
}
?>