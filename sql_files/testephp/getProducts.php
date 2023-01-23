<?php
header('Content-Type: application/json charset=utf-8');

$productList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    $result = $conn->query("SELECT * FROM product");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){
           $productList[] = new Product($row->name, $row->price);
        }
        //echo ("Response successfull!\n");
    }else{
        //echo ("Response failed: empty response");
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