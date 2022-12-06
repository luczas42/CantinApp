<?php

$response = array();
$response["erro"] = false;

if($_SERVER['REQUEST_METHOD']=='GET'){

    $response["erro"] = false;
    $response["data"] = "03/10/2018";
    echo json_encode($response);

}

?>