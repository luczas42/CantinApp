<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['id_turn']) && !empty($_POST['emp_id_array'])){

        $id_turn = $_POST['id_turn'];
        $emp_id_array = $_POST['emp_id_array'];
        

        foreach($emp_id_array as $id){
            $sql = "insert into scale (id_employee, id_turn) values (?, ?);";
            $stmt = $conn->prepare($sql);
            $stmt->bind_param('ii', $id, $id_turn);
            $stmt->execute();
        }
    }
    $conn->close();
}


?>