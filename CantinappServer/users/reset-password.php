<?php
    if (isset($_POST["reset-password-submit"])) {
        $selector = $_POST["selector"];
        $validator = $_POST["validator"];
        $password = $_POST["pwd"];
        $passwordCheck = $_POST["pwdcheck"];

        if (empty($password || empty($passwordCheck))){
            header("Location: passwordConfirm.php?newpwd=empty");
            exit();
        }else if ($password != $passwordCheck) {
            header("Location: passwordConfirm.php?newpwd=pwdnotsame");
            exit();
        }
        $currentDate = date("U");

        include '../setupConnection.php';   

        $sql = "SELECT * FROM passwordreset WHERE pwdResetSelector=? AND pwdResetExpires>=?;";
        $stmt = mysqli_stmt_init($conn);
        if (!mysqli_stmt_prepare($stmt,$sql)) {
            echo "Erro";
            exit();
        }else{
            mysqli_stmt_bind_param($stmt, "ss", $selector,$currentDate);
            mysqli_stmt_execute($stmt);

            $result = mysqli_stmt_get_result($stmt);
            if (!$row = mysqli_fetch_assoc($result)) {
                echo "Esse pedido expirou";
                exit();
            }else{
                $tokenBin = hex2bin($validator);
                $tokenCheck = password_verify($tokenBin, $row["pwdResetToken"]);

                if ($tokenCheck === false) {
                    echo "erro";
                    exit();
                }elseif ($tokenCheck === true) {
                    $tokenEmail = $row['pwdResetEmail'];
                    $sql = "SELECT * FROM user WHERE email=?;";
                    $stmt = mysqli_stmt_init($conn);
                    
                    if (!mysqli_stmt_prepare($stmt,$sql)) {
                        echo "Erro";
                        exit();
                    }else{
                        mysqli_stmt_bind_param($stmt, "s", $tokenEmail);
                        mysqli_stmt_execute($stmt);

                        $result = mysqli_stmt_get_result($stmt);
                        if (!$row = mysqli_fetch_assoc($result)) {
                            echo "Erro!";
                            exit();
                        }else{
                            $sql = "UPDATE user SET password=? WHERE email=?";
                            $stmt = mysqli_stmt_init($conn);
                    
                            if (!mysqli_stmt_prepare($stmt,$sql)) {
                                echo "Erro";
                                exit();
                            }else{
                                $newPwdHash = password_hash($password, PASSWORD_DEFAULT);
                                mysqli_stmt_bind_param($stmt, "ss", $newPwdHash, $tokenEmail);
                                mysqli_stmt_execute($stmt);

                                $sql = "DELETE FROM passwordreset WHERE pwdResetEmail=?;";
                                $stmt = mysqli_stmt_init($conn);
                                if (!mysqli_stmt_prepare($stmt,$sql)) {
                                    echo "Erro";
                                    exit();
                                }else{
                                    mysqli_stmt_bind_param($stmt, "s", $tokenEmail);
                                    mysqli_stmt_execute($stmt);
                                    header("Location: passwordConfirm.php?newpwd=passwordupdated");
                                }
                            }
                        }
                    }
                }
            }
        }

    }else{
        echo "erro";
    }
?>