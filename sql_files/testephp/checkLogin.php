<?php
header('Content-Type: application/json charset=utf-8');


$user = null;
if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['username']) && !empty($_POST['password'])){

        $username = $_POST['username'];
        $password = password_hash($_POST['password'], PASSWORD_DEFAULT);

        $sql = "select username, name, email, isUser from user where username = ? and password = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('ss', $username, $password);
        $stmt->execute();

        if($stmt->get_result()->num_rows>0){
            while($row = $stmt->get_result()->fetch_object()){
                $user = new User($row->username, $row->name, $row->email, $row->isUser);
            }
        }
    }
    json_encode($user);
    $conn->close();
}

class user{

    public $username;
    public $name;
    public $email;
    public $isUser;

    public function __construct($username, $name, $email, $isUser){
        $this->username = $username;
        $this->name = $name;
        $this->email = $email;
        $this->isUser = $isUser;
    }

}


?>