<?php
header('Content-Type: application/json charset=utf-8');

$productList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    $result = $conn->query("SELECT * FROM product");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){
            $image_path = "images/" . $row->image;
            $image_data = file_get_contents($image_path);
            $productList[] = new Product($row->id, $row->name, $row->price, $image_data, $row->productType);
        }
    }

    $conn->close();
}

header("Content-Type: application/json");
echo json_encode($productList);

class Product{

    public $id;
    public $name;
    public $price;
    public $image;
    public $productType;

    public function __construct($id, $name, $price, $image, $productType)
    {
        $this->id = $id;
        $this->name = $name;
        $this->price = $price;
        $this->image = base64_encode($image);
        $this->productType = $productType;
    }
}
?>
