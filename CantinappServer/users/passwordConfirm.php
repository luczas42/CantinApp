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
		input[type="text"] {
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
		if (isset($_GET["newpwd"])) {
			if ($_GET["newpwd"]=="passwordupdated") {
				echo('<p>Senha alterada com Sucesso!</p>');
			}
		}
?>
</body>
</html>