<?php
header('Content-Type: application/json charset=utf-8');

$turnList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    $result = $conn->query("SELECT * FROM turn");

    if($result->num_rows>0){
        while($row = $result->fetch_object()){
           $turnList[] = new Turn($row->id, $row->day, $row->period, $row->class);
        }
    }else{
    }

    $conn->close();
}

echo json_encode($turnList);

class Turn{

    public $id;
    public $day;

    public $period;

    public $class;
    
    public function __construct($id, $day, $period, $class)
    {
        $this->id = $id;
        $this->day = $day;
        $this->period = $period;
        $this->class = $class;
    }
}

?>