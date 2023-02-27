<?php
header('Content-Type: application/json charset=utf-8');


if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include '../setupConnection.php';

    if (
        !empty($_POST['day']) && !empty($_POST['period'])
        && !empty($_POST['class'] && !empty($_POST['employeeArray']))
    ) {

        $day = $_POST['day'];
        $period = $_POST['period'];
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

        $formattedPeriod;
        switch ($period) {
            case strcasecmp($period, "manhã")==0:
                $formattedPeriod = 0;
                break;
            case strcasecmp($period, "tarde")==0:
                $formattedPeriod = 1;
                break;
            case strcasecmp($period, "noite")==0:
                $formattedPeriod = 2;
                break;
        }

	$day = new DateTime($row->day);
        $timestamp = $day->getTimestamp(); // Unix timestamp
        $formattedDate = $day->format('Y-m-d'); 

        $sql = 'INSERT INTO turn (day, period, class) VALUES (?, ?, ?);';
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sii', $formattedDate, $formattedPeriod, $formattedClass);
        $stmt->execute();

        $sql = 'SELECT * FROM turn WHERE day = ? AND period = ? AND class = ?;';
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('sii', $day, $formattedPeriod, $formattedClass);
        $stmt->execute();
        $result = $stmt->get_result();
        $turnId;
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_object()) {
                $turnId = $row->id;
            }
        }



        $employeeArray = $_POST['employeeArray'];

        $sql = 'INSERT INTO scale (id_employee, id_turn) VALUES (?, ?)';

        for ($i = 0; $i < sizeof($employeeArray); $i++) {
            $stmt = $conn->prepare($sql);
            $stmt->bind_param('ii', $employeeArray[$i], $turnId);
            $stmt->execute();
        }
    }

    $conn->close();
}
