<?php
header('Content-Type: application/json charset=utf-8');

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    if(!empty($_POST['id']) 
    && !empty($_POST['day']) 
    && !empty($_POST['period']) 
    && !empty($_POST['class'])){

        $id = $_POST['id'];
        $day = $_POST['day'];
        $period = $_POST['period'];
        $class = $_POST['class'];

        $formattedClass;
        switch ($class) {
            case strcasecmp($class, "INF4AM") == 0:
                $formattedClass = 0;
                break;
            case strcasecmp($class, "INF4AT") == 0:
                $formattedClass = 1;
                break;
            case strcasecmp($class, "REFRI4AM") == 0:
                $formattedClass = 2;
                break;
            default:
                break;
        }

        $formattedPeriod;
        switch ($period) {
            case strcasecmp($period, "manhã") == 0:
                $formattedPeriod = 0;
                break;
            case strcasecmp($period, "tarde") == 0:
                $formattedPeriod = 1;
                break;
            case strcasecmp($period, "noite") == 0:
                $formattedPeriod = 2;
                break;
            default:
                break;
        }

        $sql = "UPDATE turn SET day = ?, period = ?, class = ? WHERE id = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('siii', $day, $formattedPeriod, $formattedClass, $id);
        $stmt->execute(); 
    }
    $conn->close();
}


?>