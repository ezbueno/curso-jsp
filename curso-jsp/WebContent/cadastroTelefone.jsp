<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Telefones</title>
	<link rel="stylesheet" href="resources/css/cadastro.css">	
</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Início" src="resources/img/inicio.png" width="30px" height="30px"></a>
	<a href="index.jsp"><img alt="Sair" src="resources/img/sair.png" width="30px" height="30px"></a>
	<h1>Cadastro de Telefones</h1>
	<h3>${msg}</h3>
	<h3 id="msgSalvarAtualizarExcluir">${msgSalvarAtualizarExcluir}</h3>
	<form action="salvarTelefone" method="post" onsubmit="return validarCampos() ? true : false">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${userEscolhido.id}" class="field-long"></td>
						<td><input type="text" readonly="readonly" id="nome" name="nome" value="${userEscolhido.nome}" class="field-long"></td>
					</tr>
					
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"></td>
						
						<td>
						<select id="tipo" name="tipo">
							<option>Casa</option>
							<option>Recado</option>
							<option>Celular</option>
						</select>
						</td>
						
					</tr>
										
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<table class="tabela-telefones">
	<caption><strong>Telefones cadastrados</strong></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Número</th>
				<th>Tipo</th>
				<th>Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td style="width: 250px"><c:out value="${fone.id}"></c:out></td>
					<td style="width: 250px"><c:out value="${fone.numero}"></c:out></td>
					<td style="width: 250px"><c:out value="${fone.tipo}"></c:out></td>
					
					<td><a href="salvarTelefone?acao=deleteFone&foneId=${fone.id}"><img alt="Excluir" src="resources/img/excluir.png" title="Excluir" width="20px" height="20px"></a></td>	
				</tr>				
			</c:forEach>
	</table>	
		<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == "") {
				alert("ATENÇÃO! Informe o número do telefone!");
				return false;
			} else if (document.getElementById("tipo").value == "") {
				alert("ATENÇÃO! Informe o tipo do telefone!");
				return false;
			return true;
		}
		</script>	
		<div id="link"><a id="back" href="salvarUsuario?acao=listartodos"><img alt="Voltar" src="resources/img/voltar.png" width="30px" height="30px"></a></div>	
</body>
</html>