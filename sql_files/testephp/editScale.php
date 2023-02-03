<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['id']) && !empty($_POST['employee_id'])){

        $id = $_POST['id'];
        $employee_id = $_POST['employee_id'];

        $sql = "UPDATE scale SET id_employee = ? WHERE id = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('ii', $employee_id, $id);
        $stmt->execute(); 
    }
    $conn->close();
}


?>