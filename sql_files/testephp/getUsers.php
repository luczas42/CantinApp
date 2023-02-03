<?php
header('Content-Type: application/json charset=utf-8');

$productList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include ../'setupConnection.php';

    $result = $conn->query("SELECT * FROM user");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){
           $productList[] = new User($row-> id, $row->username, $row->name, $row->password, $row-> isUser, $row->email);
        }
    }

    $conn->close();
}

echo json_encode($productList);

class User{

    public $id;
    public $username;
    public $name;
    public $password;
    public $isUser;
    public $email;
    

    public function __construct($id, $username, $name, $password, $isUser, $email)
    {
        $this->name = $name;
        $this->username = $username;
        $this->password = $password;
        $this->isUser = $isUser;
        $this->email = $email;
        $this->id = $id;
    }
}

?>