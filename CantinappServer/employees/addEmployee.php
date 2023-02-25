<?php
header('Content-Type: application/json charset=utf-8');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include '../setupConnection.php';

    if (!empty($_POST['name']) && !empty($_POST['class'])) {

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
        $sql = "INSERT INTO employee (name, class)
        VALUES (?, ?)";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('si', $name, $formattedClass);
        $stmt->execute();
    }
    $conn->close();
}
