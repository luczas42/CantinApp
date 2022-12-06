<?php
    $ambiente = false;
    if($ambiente){ // ambiente de produção
        $HostName = "";
        $HostUser = "";
        $HostPass = "";
        $DatabaseName = "";

        print("daaaaaaaaaaa");
    }else{ //ambiente de desenvolvimento
        $HostName = "localhost";
        $HostUser = "root";
        $HostPass = "";
        $DatabaseName = "cantinapp";
    }
?>