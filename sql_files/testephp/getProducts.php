<?php
header('Content-Type: application/json charset=utf-8');

//variável que armazena os dados que vao virar json
$productList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'dbConnection.php';

    //variável com a conexão
    $conn = new mysqli($HostName, $HostUser,  
    $HostPass, $DatabaseName); 

    mysqli_set_charset($conn, "utf8");

    if($conn->connect_error){
        die("Connection failed: ". $conn->connect_error);
    }

    //variável da query no bd
    $result = $conn->query("SELECT * FROM product");

    if($result->num_rows>0){

        //lista de objetos que guarda o resultado da query
        while($row = $result->fetch_object()){

           $productList[] = new Product($row->name, $row->price);

        }
    }else{
    }

    $conn->close();
}

echo json_encode($productList);

class Product{

    public $name;
    public $price;

    public function __construct(string $name, float $price)
    {
        $this->name = $name;
        $this->price = $price;
    }
}

?>