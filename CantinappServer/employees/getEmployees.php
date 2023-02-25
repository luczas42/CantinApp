<?php
header('Content-Type: application/json charset=utf-8');

$employeeList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    $result = $conn->query("SELECT * FROM employee;");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){
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
    $conn->close();
}

class Employee{
    public $id;
    public $name;
    public $class;

    public function __construct($id, $name, $class)
    {
        $this->id= $id;
        $this->name = $name;
        $this->class = $class;
    }
}
?>