<?php
header('Content-Type: application/json charset=utf-8');

$employeeList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['class'])){

        $class = $_POST['class'];

        $sql = "SELECT * FROM employee 
        WHERE class = ?";

        $stmt = $conn->prepare($sql);
        $stmt->bind_param('s', $class);
        $stmt->execute();
        $result = $stmt->get_result();

        if($result->num_rows>0){
            while($row = $result->fetch_object()){
               $employeeList[] = $row->name;
            }
        }else{
        }
    }
    echo json_encode($employeeList);
    $conn->close();
}



?>