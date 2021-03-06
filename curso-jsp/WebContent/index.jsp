<jsp:useBean id="cadastro" class="beans.BeanUsuario" type="beans.BeanUsuario" scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Meu Projeto JSP</title>
		<link rel="stylesheet" href="resources/css/estilo.css">	
	</head>
	<body>
	<h3>${msg}</h3>
	<div class="login-page">
	  	<div class="form">
			<form action="LoginServlet" method="post" class="login-form" onsubmit="return validarLoginSenha() ? true : false">
				Login:
				<input type="text" id="login" name="login">
				<br>
				Senha:
				<input type="password" id="senha" name="senha">
				<br>
				<button type="submit" value="Acessar">Acessar</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function validarLoginSenha() {
			var login = document.getElementById("login").value;
			var senha = document.getElementById("senha").value;
			if (login == "" && senha == "") {
				alert("ATENÇÃO! Favor informar o login e a senha!");
				return false;
			} else if (login == "" && senha != "") {
				alert("ATENÇÃO! Favor informar o login!");
				return false;
			}  else if (login != "" && senha == "") {
				alert("ATENÇÃO! Favor informar a senha!");
				return false;
			}
			return true;
		}
	</script>
	<div class="footer">
  		<p>Projeto Didático - Desenvolvido em JSP + Servlets + JDBC</p>
  		<p>Login: admin | Senha: admin</p>
	</div>
	</body>
</html>