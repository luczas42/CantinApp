<?php
header('Content-Type: application/json charset=utf-8');

$employeeList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    $result = $conn->query("SELECT s.id, e.name, e.class, t.day, t.period
    FROM employee e, scale s, turn t
    WHERE e.id = s.id_employee
    AND s.id_turn = t.id;");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){

            $employeeList[] = new Employee($row->id, $row->name, $row->class, $row->day, $row->period);

            // $auxArray[] = $row->day;
        }

        // for ($i; $i < count($auxArray); $i++){
        //     echo ($auxArray[$i]);
        //     echo ("\n"); //enquanto $i for menor que o número de indexes de $aux array
        //     for($j; $j<count($dayArray); $j++){ // enquanto $j for menor que o número de indexes de $day array
        //         if($dayArray[$j]!=$auxArray[$i]){
        //             $dayArray[$j] = $auxArray[$i];
        //             echo ($dayArray[$j]);
        //         echo ("\n");
        //         }
        //     }
        // }
    }else{
    }

    echo json_encode($employeeList);
    $conn->close();

}
// echo json_encode($employeeList);

class Employee{
    public $id;
    public $name;
    public $class;
    public $day;
    public $period;

    public function __construct($id, $name, $class, $day, $period)
    {
        $this->id = $id;
        $this->name = $name;
        $this->class = $class;
        $this->day = $day;
        $this->period = $period;
    }
}

?>