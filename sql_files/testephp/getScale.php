<?php
header('Content-Type: application/json charset=utf-8');

$scaleList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'dbConnectionTest.php';

    $conn = new mysqli ($HostName, $HostUser, $HostPass, $DatabaseName);

    mysqli_set_charset($conn, "utf8");

    if($conn->connect_error){
        die("Connection failed: ". $conn->connect_error);
    }

    // escrever o comando completo quando eu for testá-lo
    $result = $conn->query("SELECT employee.name, employee.class, turn.day, turn.period
    FROM scale
    JOIN (employee, turn)
    ON (scale.id_employee = employee.id AND scale.id_turn = turn.id);");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){
            $scaleList[] = new Scale($row->name, $row->class, $row->day, $row->period);
        }
    //programar alguma coisa no else
    }else{
        print "error";
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