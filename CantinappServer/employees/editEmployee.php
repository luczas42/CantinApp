<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['id']) && !empty($_POST['name']) && !empty($_POST['class'])){

        $id = $_POST['id'];
        $name = $_POST['name'];
        $class = $_POST['class'];

        $formattedClass;

        switch ($class) {
            case strcasecmp($class, "inf4am")==0:
                $formattedClass = 0;
                break;
            case strcasecmp($class, "inf4at")==0:
                $formattedClass = 1;
                break;
            case strcasecmp($class, "refri4am")==0:
                $formattedClass = 2;
                break;
        }

        print($formattedClass);

        $sql = "UPDATE employee SET name = ?, class = ? WHERE id = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sii', $name, $formattedClass, $id);
        $stmt->execute(); 
    }
    $conn->close();
}


?>