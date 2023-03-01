<?php
header('Content-Type: application/json charset=utf-8');


$user = array();
if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['username']) && !empty($_POST['password'])){

        $username = $_POST['username'];
        $password = $_POST['password'];

        $sql = "select password from user where username = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('s', $username);
        $stmt->execute();
        $dbpassword = null;

        $result = $stmt->get_result();

        if($result->num_rows>0){
            while ($row = $result->fetch_object()){
                $dbpassword = $row->password;
            }
        }
        

        if(password_verify($password, $dbpassword)){
            $sql = "select id, username, name, email, isUser from user where username = ?;";
            $stmt = $conn->prepare($sql);
            $stmt->bind_param('s', $username);
            $stmt->execute();
            
            $result = $stmt->get_result();
    
            if($result->num_rows>0){
                while($row = $result->fetch_object()){
                    $user[] = new User($row->id, $row->username, $row->name, $row->email, $row->isUser);
                    // $newUser = new User($row->id, $row->username, $row->name, $row->email, $row->isUser);
		    // $user['success'] = false;
		    // $user['user'] = $newUser;
                }
            }
        }
    }
    echo json_encode($user);
    $conn->close();
}

class user{

    public $id;
    public $username;
    public $name;
    public $email;
    public $isUser;

    public function __construct($id, $username, $name, $email, $isUser){
        $this->id = $id;
        $this->username = $username;
        $this->name = $name;
        $this->email = $email;
        $this->isUser = $isUser;
    }

}


?>