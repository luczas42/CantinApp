<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';
$image_name = $_POST["image_name"];
$image_path = "images/" . $image_name;
if (file_exists($image_path)) {
    header("Content-Type: image/*");
    readfile($image_path);
} else {
}
}
?>