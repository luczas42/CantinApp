<?php
header('Content-Type: application/json charset=utf-8');

$scaleList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['turn_id'])){

        $turn_id = $_POST['turn_id'];

        $sql = "SELECT e.id, e.name 
        FROM employee e, scale s
        WHERE s.id_employee = e.id
        AND s.id_turn = ?";

        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $turn_id);
        $stmt->execute();
        $result = $stmt->get_result();

        if($result->num_rows>0){
            while($row = $result->fetch_object()){
                $scaleList[] = new Employee($row->id, $row->name);
            }
        }
    }

    echo json_encode($scaleList);
    $conn->close();

}

class Employee{
    public $id;

    public $name;

    public function __construct($id, $name)
    {
        $this->id = $id;
        $this->name = $name;
    }
}

?>