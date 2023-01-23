<?php
header('Content-Type: application/json charset=utf-8');

$scaleList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    $result = $conn->query("SELECT employee.name, employee.class, turn.day, turn.period
    FROM scale
    JOIN (employee, turn)
    ON (scale.id_employee = employee.id AND scale.id_turn = turn.id);");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){
            $scaleList[] = new Scale($row->name, $row->class, $row->day, $row->period);
        }
        //echo ("Response successfull!\n");
    }else{
        //echo ("Response failed: Empty Response");
    }

    $conn->close();

}
echo json_encode($scaleList);

class Scale{
    public $day;
    public $period;
    public $name;
    public $class;

    public function __construct($name, $class, $day, $period)
    {
        $this->day = $day;
        $this->period = $period;
        $this->name = $name;
        $this->class = $class;
    }
}

?>