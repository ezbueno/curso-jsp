<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuários</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<h1>Cadastro de Usuários</h1>
	<h3>${msg}</h3>
	<form action="salvarUsuario" method="post" id="formUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" class="field-long"></td>
					</tr>
					
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" class="field-long"></td>
					</tr>
					 
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" class="field-long"></td>
					</tr>					
					
					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone"
							value="${user.telefone}" class="field-long"></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'"></td>
					</tr>

				</table>

			</li>
		</ul>
	</form>

	<table class="tabela-usuarios">
	<caption><strong>Usuários cadastrados</strong></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Usuário</th>
				<!-- <th>Senha</th> -->
				<th>Nome</th>
				<th>Telefone</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 150px"><c:out value="${user.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${user.login}"></c:out>
					</td>
					<!-- <td><c:out value="${user.senha}"></c:out></td> -->
					<td style="width: 150px"><c:out value="${user.nome}"></c:out>
					<td style="width: 150px"><c:out value="${user.telefone}"></c:out>
					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img alt="Excluir" src="resources/img/excluir.png" title="Excluir" width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img alt="Editar" src="resources/img/editar.png" title="Editar" width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
	</table>

</body>
</html>