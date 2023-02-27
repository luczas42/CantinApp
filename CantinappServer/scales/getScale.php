<?php
header('Content-Type: application/json charset=utf-8');

$scaleList = array();
$turnList = array();
$finalList = array();

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

            $day = new DateTime($row->day);
            $timestamp = $day->getTimestamp(); // Unix timestamp
            $formattedDate = $day->format('d/m/Y'); 

            $turnList[] = new Turn($row->id, $formattedDate, $formattedPeriod, $formattedClass);
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
            $scaleList[] = new Employee($row->id_emp, $row->name, $formattedClass, $row->id_turn, $row->id_scale);
        }
    }

    foreach ($turnList as $keyTurn => $turn) {
        foreach ($scaleList as $keyScale => $scale) {
            if ($scale->turn_id == $turn->id) {
                $turn->employee_array[] = $scale;
            }
        }
    }
    echo json_encode($turnList);
    $conn->close();
}


class Employee
{

    public $id;

    public $name;

    public $class;

    public $turn_id;

    public $scale_id;

    public function __construct($id, $name, $class, $turn_id, $scale_id)
    {
        $this->id = $id;
        $this->name = $name;
        $this->class = $class;
        $this->turn_id = $turn_id;
        $this->scale_id = $scale_id;
    }

    
}

class Turn
{

    public $id;

    public $day;

    public $period;

    public $class;

    public $employee_array;

    public function __construct($id, $day, $period, $class)
    {
        $this->id = $id;
        $this->day = $day;
        $this->period = $period;
        $this->class = $class;
    }
 
    public function setEmployee_array($employee_array)
    {
        $this->employee_array = $employee_array;
        return $this;
    }
}
