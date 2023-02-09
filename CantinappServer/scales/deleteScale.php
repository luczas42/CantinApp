<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['id_array'])){
        $id_array = $_POST['id_array'];

        foreach($id_array as $id){
            $sql = "DELETE FROM scale WHERE id = ?;";
            $stmt = $conn->prepare($sql);
            $stmt->bind_param('i', $id);
            $stmt->execute(); 
        }
    }
    $conn->close();
}


?>