<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['id_turn']) && !empty($_POST['emp_num'])){

        $id_turn = $_POST['id_turn'];
        $emp_num = $_POST['emp_num'];
        switch ($emp_num){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

        $sql = "insert into scale (id_employee, id_turn) values (?, ?);";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('ii', $employee_id, $turn_id);
        $stmt->execute();
    }else{
    }
    $conn->close();
}


?>