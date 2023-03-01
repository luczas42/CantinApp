<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

require 'phpmailer/src/Exception.php';
require 'phpmailer/src/PHPMailer.php';
require 'phpmailer/src/SMTP.php';
if (isset($_POST["reset-request-submit"])) {
    
    $selector = bin2hex(random_bytes(8));
    
    $token = random_bytes(32); 
    
    $url = "http://54.207.241.251/CantinappServer/users/passwordReset.php?selector=".$selector."&validator=".bin2hex($token);

    $expires = date("U") + 600;

    include '../setupConnection.php';   

    $userEmail = $_POST["email"];

    $sql = "DELETE FROM passwordreset WHERE pwdResetEmail=?;";
    $stmt = mysqli_stmt_init($conn);
    if (!mysqli_stmt_prepare($stmt,$sql)) {
        echo "Erro";
        exit();
    }else{
        mysqli_stmt_bind_param($stmt, "s", $userEmail);
        mysqli_stmt_execute($stmt);
    }

    $sql = "INSERT INTO passwordreset(pwdResetEmail, pwdResetSelector, pwdResetToken, pwdResetExpires) VALUES (?,?,?,?);";
    $stmt = mysqli_stmt_init($conn);
    if (!mysqli_stmt_prepare($stmt,$sql)) {
        echo "Erro";
        exit();
    }else{
        $hashedToken = password_hash($token, PASSWORD_DEFAULT);
        mysqli_stmt_bind_param($stmt, "ssss", $userEmail, $selector, $hashedToken, $expires);
        mysqli_stmt_execute($stmt);
    }

    mysqli_stmt_close($stmt);
    mysqli_close($conn);

    $to = $userEmail;

    $subject = "Redefinição de senha - Cantinapp";

    $message = '<p>Recebemos um pedido de redefinição de sua senha. O link para realizar a redefinição se encontra abaixo. Se não foi você que fez essa requisição por favor ignore esse email</p>';
    $message .= '<p>Link:</br>';
    $message .= '<a href="'. $url .'">'. $url .'</a></p>';



    $mail = new PHPMailer(true);
    try {
$mail->CharSet = "UTF-8";
	$mail->SMTPDebug = 0;                     
        $mail->isSMTP();
        $mail->SMTPAuth=true;
        $mail->SMTPSecure='ssl';
        $mail->Host='smtp.gmail.com';
        $mail->Port=465;
        $mail->isHTML();
        $mail->Username = 'thecantinapp@gmail.com';
        $mail->Password = 'lkpdffloascdtyni';
        $mail->setFrom('thecantinapp@gmail.com');
        $mail->Subject = $subject;
        $mail->Body = $message;
        $mail->addAddress($to);
        $mail->send();

        header("Location: passwordEmailReset.php?reset=success");
    
    } catch (\Throwable $th) {
        echo($mail->ErrorInfo);
    }

}
?>