<?php
header('Content-Type: application/json charset=utf-8');

$scaleList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'dbConnection.php';

    $conn = new mysqli ($HostName, $HostUser, $HostPass, $DatabaseName);

    mysqli_set_charset($conn, "urf-8");

    if($conn->connect_error){
        die("Connection failed: ". $conn->connect_error);
    }

    // escrever o comando completo quando eu for testá-lo
    $result = $conn->query("SELECT * FROM ");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){

            $scaleList[] = new Scale();

        }
    //programar alguma coisa no else
    }else{

    }

    echo json_encode($scaleList);
}

class Scale {
    // variáveis:
    //array de employees
    //array de turnos
}

class Turn{
    //variáveis:
    //data
    //período(manhã, tarde, noite)
}

class Employee{
    //variáveis:
    //nome
    //turma
}

?>