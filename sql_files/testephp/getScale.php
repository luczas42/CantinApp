<?php
header('Content-Type: application/json charset=utf-8');

$scaleList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    $result = $conn->query("SELECT employee.name, employee.class, employee.id as 'id_employee', turn.day, turn.period, turn.id as 'id_turn'
    FROM scale
    JOIN (employee, turn)
    ON (scale.id_employee = employee.id AND scale.id_turn = turn.id);");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){
            $scaleList[] = new Scale($row->name, $row->class, $row->id_employee, $row->day, $row->period,  $row->id_turn);
        }
    }else{
    }

    $conn->close();

}
echo json_encode($scaleList);

class Scale{
    public $name;
    public $class;
    public $id_employee;
    public $day;
    public $period;
    public $id_turn;

    public function __construct($name, $class, $id_employee, $day, $period, $id_turn)
    {
        $this->day = $day;
        $this->period = $period;
        $this->id_employee = $id_employee;
        $this->name = $name;
        $this->class = $class;
        $this->id_turn = $id_turn;
    }
}

?>