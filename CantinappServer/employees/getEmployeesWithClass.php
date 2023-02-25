<?php
header('Content-Type: application/json charset=utf-8');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include '../setupConnection.php';

    if (!empty($_POST['class'])) {

        $class = $_POST['class'];
        $formattedClass;

        switch ($class) {
            case strcasecmp($class, "inf4am") == 0:
                $formattedClass = 0;
                break;
            case strcasecmp($class, "inf4at") == 0:
                $formattedClass = 1;
                break;
            case strcasecmp($class, "refri4am") == 0:
                $formattedClass = 2;
                break;
        }

        $sql = "SELECT * FROM employee WHERE class = ?;";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param('i', $formattedClass);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows > 0) {
            while ($row = $result->fetch_object()) {
                $formattedClass;
                $class = $row->class;

                switch ($class) {
                    case 0:
                        $formattedClass = "INF4AM";
                        break;
                    case 1:
                        $formattedClass = "INF4AT";
                        break;
                    case  2:
                        $formattedClass = "REFRI4AM";
                        break;
                }
                $employeeList[] = new Employee($row->id, $row->name, $formattedClass);
            }
        }
        echo json_encode($employeeList);
    }
    $conn->close();
}
class Employee
{
    public $id;
    public $name;
    public $class;

    public function __construct($id, $name, $class)
    {
        $this->id = $id;
        $this->name = $name;
        $this->class = $class;
    }
}
