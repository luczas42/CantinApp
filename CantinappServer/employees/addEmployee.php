<?php
header('Content-Type: application/json charset=utf-8');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include '../setupConnection.php';

    if (!empty($_POST['name']) && !empty($_POST['class'])) {

        $name = $_POST['name'];
        $class = $_POST['class'];
        $formattedClass;

        switch ($class) {
            case strcasecmp($class, "INF4AM")==0:
                $formattedClass = 0;
                break;
            case strcasecmp($class, "INF4AT")==0:
                $formattedClass = 1;
                break;
            case strcasecmp($class, "REFRI4AM")==0:
                $formattedClass = 2;
                break;
            default:
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
