<?php
header('Content-Type: application/json charset=utf-8');

$employeeList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    $result = $conn->query("SELECT e.name, e.class, t.day, t.period
    FROM employee e, scale s, turn t
    WHERE e.id = s.id_employee
    AND s.id_turn = t.id;");

    if($result->num_rows>0){

        $i = 0;
        $j = 0;
        $auxArray = array();
        $dayArray = array(
            0 => 0,
            1 => 1
        );

        while($row = $result->fetch_object()){

            $employeeList[] = new Employee($row->name, $row->class, $row->day, $row->period);

            $auxArray[] = $row->day;
        }

        for ($i; $i < count($auxArray); $i++){
            echo ($auxArray[$i]);
            echo ("\n"); //enquanto $i for menor que o número de indexes de $aux array
            for($j; $j<count($dayArray); $j++){ // enquanto $j for menor que o número de indexes de $day array
                echo ($dayArray[$j]);
                echo ("\n");
                // if($dayArray[$j]!=$auxArray[$i]){
                //     $dayArray[$i] = $auxArray[$i];
                // }
            }
        }
        //echo ("Response successfull!\n");
    }else{
        //echo ("Response failed: Empty Response");
    }

    $conn->close();

}
// echo json_encode($employeeList);

class Employee{
    public $name;
    public $class;
    public $day;
    public $period;

    public function __construct($name, $class, $day, $period)
    {
        $this->name = $name;
        $this->class = $class;
        $this->day = $day;
        $this->period = $period;
    }
}

?>