<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['username']) && !empty($_POST['name']) 
    && !empty($_POST['password']) && !empty($_POST['isUser']) 
    && !empty($_POST['email'])){

        $username = $_POST['username'];
        $name = $_POST['name'];
        $password = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $isUser = $_POST['isUser'];
        $email = $_POST['email'];

        $sql = "INSERT INTO user (username, name, password, isUser, email) VALUES (?, ?, ?, ?, ?);";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sssss', $username, $name, $password, $isUser, $email);
        $stmt->execute();
    }
    $conn->close();
}


?>