<?php
header('Content-Type: application/json charset=utf-8');

$employeeArray = array();
$turnArray = array();
$turnIdArray = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include '../setupConnection.php';

    $result = $conn->query("SELECT * FROM turn");

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

            $formattedPeriod;
            $period = $row->period;

            switch ($period) {
                case 0:
                    $formattedPeriod = "Manhã";
                    break;
                case 1:
                    $formattedPeriod = "Tarde";
                    break;
                case  2:
                    $formattedPeriod = "Noite";
                    break;
            }
            $turnArray[] = new Turn($row->id, $row->day, $formattedPeriod, $formattedClass);
        }
    }

    $sql = "SELECT e.id as 'id_emp', 
        s.id as 'id_scale',
        e.name,
        e.class,
        t.id as 'id_turn'
        FROM employee e, scale s, turn t
        WHERE s.id_employee = e.id
        AND s.id_turn = t.id
        ORDER BY e.id;";

    $stmt = $conn->prepare($sql);
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

            $employeeArray[] = new Employee($row->id_emp, $row->name, $formattedClass, $row->id_scale);
            $turnIdArray[] = $row->id_turn;
        }
    }

    foreach ($turnArray as $turn) {
        foreach ($turnIdArray as $turnId) {
            if ($turnId == $turn->id) {
                $turn->employeeArray[] = $employeeArray;
            }
        }
    }
    echo json_encode($turnArray);
    $conn->close();
}


class Employee
{

    public $id;

    public $name;

    public $class;

    public $scaleId;

    public function __construct($id, $name, $class, $scaleId)
    {
        $this->id = $id;
        $this->name = $name;
        $this->class = $class;
        $this->scaleId = $scaleId;
    }

    
}

class Turn
{

    public $id;
    public $day;

    public $period;

    public $class;

    public $employeeArray;

    public function __construct($id, $day, $period, $class)
    {
        $this->id = $id;
        $this->day = $day;
        $this->period = $period;
        $this->class = $class;
    }
 
    public function setEmployee_array($employeeArray)
    {
        $this->employeeArray = $employeeArray;
        return $this;
    }
}
