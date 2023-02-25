<?php
header('Content-Type: application/json charset=utf-8');

$turnList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include '../setupConnection.php';

    $result = $conn->query("SELECT * FROM turn");

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
           $turnList[] = new Turn($row->id, $row->day, $formattedPeriod, $formattedClass);
        }
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