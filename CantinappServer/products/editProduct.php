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
            $stmt1 = $conn->prepare("SELECT * FROM product WHERE id=$pid");
            $stmt1->execute();
            $result = $stmt1->get_result();
            $product = $result->fetch_assoc();

            if ($product) {
                $sql = "UPDATE product SET name=?, price=?, image=?, productType=? WHERE id=$pid";
                $stmt = $conn->prepare($sql);
                $stmt->bind_param('sdsi', $pname, $price, $product["image"], $productType );
                $stmt->execute();
                $response = array("success" => true, "message" => "Funcionou???");
                echo json_encode($response);
            } else {
              $response = array("success" => false, "message" => "Não foi possível encontrar o produto com id " . $pid);
                echo json_encode($response);
            }
        }
    } else {
        $response = array("success" => false, "message" => "Parametros");
        echo json_encode($response);
    }

    $conn->close();
}
?>
