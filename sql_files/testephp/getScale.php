<?php
header('Content-Type: application/json charset=utf-8');

$scaleList = array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    include 'setupConnection.php';

    if(!empty($_POST['turn_id'])){

        $turn_id = $_POST['turn_id'];

        $sql = "SELECT s.id as 'id_scale', e.id as 'id_emp', e.name 
        FROM employee e, scale s
        WHERE s.id_employee = e.id
        AND s.id_turn = ?
        ORDER BY e.id";

        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $turn_id);
        $stmt->execute();
        $result = $stmt->get_result();

        if($result->num_rows>0){
            while($row = $result->fetch_object()){
                $scaleList[] = new Employee($row->id_emp, $row->name, $row->id_scale);
            }
        }
    }

    echo json_encode($scaleList);
    $conn->close();

}

class Employee{


    public $id;

    public $name;

    public $scale_id;

    public function __construct($id, $name, $scale_id)
    {
        $this->id = $id;
        $this->name = $name;
        $this->scale_id = $scale_id;
    }
}

?>