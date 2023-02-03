<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['turn_id']) && !empty($_POST['emp_id_array'])){

        $turn_id = $_POST['turn_id'];
        $emp_id_array = $_POST['emp_id_array'];

        foreach($emp_id_array as $id){
            $sql = "INSERT INTO scale (id_employee, id_turn) VALUES (?, ?);";
            $stmt = $conn->prepare($sql);
            $stmt->bind_param('ii', $id, $turn_id);
            $stmt->execute();
        }
    }
    $conn->close();
}


?>