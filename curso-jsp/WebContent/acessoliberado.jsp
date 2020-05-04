<%@page import="dao.DaoLogin"%>
<jsp:useBean id="cadastro" class="beans.BeanUsuario" type="beans.BeanUsuario" scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<link rel="stylesheet" href="resources/css/acessoliberado.css">
	</head>
	<body>
		<a href="index.jsp"><img alt="Sair" src="resources/img/sair.png" width="30px" height="30px" id="botao-sair" onclick="return confirm('Deseja sair do sistema?')"></a>
		<%
			DaoLogin daoLogin = new DaoLogin();
			String login = request.getParameter("login");
			String nome = daoLogin.buscarNome(login);
			session.setAttribute("nome", nome);
			
		%>	
			
		<h1>Bem-Vindo ao Sistema, 
			<% out.print(session.getAttribute("nome") + "!");%>
		</h1>
		
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