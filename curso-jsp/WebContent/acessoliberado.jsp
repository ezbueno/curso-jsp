<jsp:useBean id="cadastro" class="beans.BeanUsuario" type="beans.BeanUsuario" scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Bem-Vindo</title>
		<link rel="stylesheet" href="resources/css/acessoliberado.css">
	</head>
	<body>
		<h1>Bem-Vindo ao Sistema</h1>
		<table class="centro">
		<tr>
			<td>
				<a href="salvarUsuario?acao=listartodos"><img alt="Usuários" src="resources/img/users.png" width="200px" height="150px" title="Cadastro de Usuários"></a>
				<h4>Cadastro de Usuários</h4>
			</td>
			<td>
			<td>
				<a href="salvarProduto?acao=listartodosprodutos"><img alt="Produtos" src="resources/img/product.png" width="200px" height="150px" title="Cadastro de Produtos"></a>
				<h4>Cadastro de Produtos</h4>
			</td>
		</tr>
		</table>
	</body>
</html>