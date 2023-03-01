<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['username']) && !empty($_POST['name']) 
    && !empty($_POST['password']) && !empty($_POST['isUser']) 
    && !empty($_POST['email'])){

        $username = $_POST['username'];
        $name = $_POST['name'];
        $password = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $isUser = $_POST['isUser'];
        $email = $_POST['email'];

        $checkSql = "SELECT * FROM user WHERE username = ? OR email = ?";
        $checkStmt = $conn->prepare($checkSql);
        $checkStmt->bind_param('ss', $username, $email);
        $checkStmt->execute();
        $result = $checkStmt->get_result();
        if ($result->num_rows > 0) {
            $response = array("success" => false, "message" => "Email ou nome de usuário já existe");
            echo json_encode($response);
        } else {
            $sql = "INSERT INTO user (username, name, password, isUser, email) VALUES (?, ?, ?, ?, ?);";
            $stmt = $conn->prepare($sql);
            $stmt->bind_param('sssis', $username, $name, $password, $isUser, $email);
            $stmt->execute();

            // Return a success message
            $response = array("success" => true, "message" => "Usuário criado com sucesso");
            echo json_encode($response);
        }
    }
    $conn->close();
}
?>
