<?php
header('Content-Type: application/json charset=utf-8');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include '../setupConnection.php';

    if (!empty($_POST['pname']) && !empty($_POST['price']) && !empty($_POST['productType'])) {
        $pname = $_POST['pname'];
        $price = $_POST['price'];
        $productType = $_POST['productType'];
        $pid = $_POST['pid'];

        if (!empty($_FILES["image"]["name"])) {
            $targetDir = "images/";
            $fileName = basename($_FILES["image"]["name"]);
            $targetFilePath = $targetDir . $fileName;
            $fileType = pathinfo($targetFilePath, PATHINFO_EXTENSION);
            if (move_uploaded_file($_FILES["image"]["tmp_name"], $targetFilePath)) {
                $sql = "UPDATE product SET name=?, price=?, image=?, productType=? WHERE id=?";
                $stmt = $conn->prepare($sql);
                $stmt->bind_param("sdsii", $pname, $price, $fileName, $productType, $pid);
                $stmt->execute();
            $response = array("success" => true, "message" => "Adicionado com imagem");
            echo json_encode($response);
            } else {
            $response = array("success" => false, "message" => "Imagem não");
            echo json_encode($response);
            }
        } else {
            $sql = "UPDATE product SET name=?, price=?, productType=? WHERE id=?";
            $stmt = $conn->prepare($sql);
            $stmt->bind_param('sdii', $pname, $price, $productType, $pid);
            $stmt->execute();
            $response = array("success" => true, "message" => "Funcionou???");
            echo json_encode($response);
        }
    } else {
         $response = array("success" => false, "message" => "Parametros");
            echo json_encode($response);
    }

    $conn->close();
}
?>