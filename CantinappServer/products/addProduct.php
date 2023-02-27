<?php
header('Content-Type: application/json charset=utf-8');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include '../setupConnection.php';
    echo ('aqui entrou');

    if (!empty($_POST['pname']) && !empty($_POST['price']) && !empty($_FILES["image"]["name"]) && !empty($_POST['productType'])) {
        $pname = $_POST['pname'];
        $price = $_POST['price'];
        $productType = $_POST['productType'];
        echo ('aqui entrou');

        $targetDir = "images/";
        $fileName = basename($_FILES["image"]["name"]);
        $targetFilePath = $targetDir . $fileName;
        $fileType = pathinfo($targetFilePath, PATHINFO_EXTENSION);
        if (move_uploaded_file($_FILES["image"]["tmp_name"], $targetFilePath)) {
            $sql = "INSERT INTO product (name, price, image, productType) VALUES (?,?,?,?);";
            $stmt = $conn->prepare($sql);
            $stmt->bind_param("sdsi", $pname, $price, $fileName, $productType);
            $stmt->execute();
            echo ('aqui img');
        }
    } else if (!empty($_POST['pname']) && !empty($_POST['price']) && !empty($_POST['productType'])) {
        echo ('aqui sem img');

        $pname = $_POST['pname'];
        $price = $_POST['price'];
        $productType = $_POST['productType'];
        $sql = "INSERT INTO product (name, price, productType) VALUES (?,?,?);";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sdi', $pname, $price, $productType);
        $stmt->execute();
    }
    echo ('aqui saiu');

    $conn->close();
}
