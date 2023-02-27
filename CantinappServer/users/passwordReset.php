<!DOCTYPE html>
<html>
<head>
	<title>Cantinapp - Redefinir de senha</title>
	<style type="text/css">
		body {
			background-color: white;
			color: green;
			font-family: Arial, sans-serif;
		}
		form {
			margin-top: 50px;
			display: flex;
			flex-direction: column;
			align-items: center;
		}
		label {
			margin-top: 20px;
			font-size: 20px;
			font-weight: bold;
		}
		input[type="password"] {
			padding: 10px;
			margin-top: 10px;
			border: 2px solid green;
			border-radius: 5px;
			font-size: 18px;
			width: 300px;
			max-width: 100%;
			box-sizing: border-box;
		}
		button[type="submit"] {
			background-color: green;
			color: white;
			padding: 10px 20px;
			border: none;
			border-radius: 5px;
			font-size: 20px;
			cursor: pointer;
			margin-top: 30px;
		}
	</style>
</head>
<body>
	<h1>Cantinapp - Redefinição de senha</h1>

	<?php
		$selector = $_GET["selector"];
		$validator = $_GET["validator"];

		if (empty($selector || empty($validator))) {
			echo('Erro');
		}else{
			if (ctype_xdigit($selector)!==false && ctype_xdigit($validator)!==false) {
				?>

<form action="reset-password.php" method="post">
		<input type="hidden" name="selector" value="<?php echo $selector?>">
		<input type="hidden" name="validator" value="<?php echo $validator?>">
		<label>Nova senha:</label>
		<input type="password" name="pwd" required>
		<label>Confirme a nova senha:</label>
		<input type="password" name="pwdcheck" required>
		<button type="submit" name="reset-password-submit">Confirmar</button>
	</form>

				<?php
			}
		}
    ?>


</body>
</html>