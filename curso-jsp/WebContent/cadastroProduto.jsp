<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Produtos</title>
<script src="resources/js/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/jquery.maskMoney.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Início" src="resources/img/inicio.png" width="30px" height="30px"></a>
	<a href="index.jsp"><img alt="Sair" src="resources/img/sair.png" width="30px" height="30px"></a>
	<h1>Cadastro de Produtos</h1>
	<h3>${msg}</h3>
	<h3 id="msgSalvarAtualizarExcluir">${msgSalvarAtualizarExcluir}</h3>
	<form action="salvarProduto" method="post" id="formProduto" onsubmit="return validarCampos() ? true : false">
		<ul class="form-style-1">
			<li>
				<table id="table-prod">
					<tr>
						<td>Código:</td>
						<c:if test="${produto.id == null}">
							<td><input type="text" readonly="readonly" id="id" name="id" style="background-color: #DCDCDC" onclick="alert('Campo de preenchimento automático.')"
								value="${produto.id}" class="field-long"></td>
						</c:if>
						
						<c:if test="${produto.id != null}">
							<td><input type="text" readonly="readonly" id="id" name="id" style="background-color: #DCDCDC" onclick="alert('Não é permitido alterar o código do produto.')"
								value="${produto.id}" class="field-long"></td>
						</c:if>
					</tr>

					<tr>
						<td>Nome:</td>
							<c:if test="${produto.nome == null}">
								<td><input type="text" id="nome" name="nome"
									value="${produto.nome}" maxlength="100" class="field-long"></td>
							</c:if>
					
							<c:if test="${produto.nome != null}">
								<td><input type="text" id="nome" name="nome" readonly="readonly" style="background-color: #DCDCDC" onclick="alert('Não é permitido alterar o nome do produto.')"
									value="${produto.nome}" maxlength="100" class="field-long"></td>
							</c:if>
					</tr>
					
					<tr>
						<td>Quantidade:</td>
							<c:if test="${produto.quantidade == null}">
								<td><input type="number" id=quantidade name="quantidade"
									value="${produto.quantidade}" maxlength="10" class="field-long"></td>
							</c:if>
	
							<c:if test="${produto.quantidade != null}">
								<td><input type="number" id=quantidade name="quantidade"
									value="<fmt:formatNumber value="${produto.quantidade}"></fmt:formatNumber>" maxlength="10" class="field-long"></td>								
							</c:if>
					</tr>
					 
					<tr>
						<td>Valor:</td>
							<c:if test="${produto.valor == null}">
							<td><input type="text" id="valor" name="valor"
								value="${produto.valor}" maxlength="12" class="field-long"></td>
							</c:if>
							
							<c:if test="${produto.valor != null}">
							<td><input type="text" id="valor" name="valor"
								value="<fmt:formatNumber value="${produto.valor}"></fmt:formatNumber>" maxlength="12" class="field-long"></td>
							</c:if>
					</tr>					
										
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 80px"> <input type="submit" value="Cancelar" onclick="document.getElementById('formProduto').action='salvarProduto?acao=reset'" style="width: 80px"></td>
					</tr>
				</table>
			</li>
		</ul>		
	</form>
	<table class="tabela-produtos">
	<caption><strong>Produtos cadastrados</strong></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td style="width: 150px"><c:out value="${produto.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${produto.nome}"></c:out></td>
					<td style="width: 150px"><fmt:formatNumber type="number" value="${produto.quantidade}"></fmt:formatNumber></td>
					<td style="width: 150px"><fmt:formatNumber type="number" value="${produto.valor}"></fmt:formatNumber></td>
					<td><a href="salvarProduto?acao=delete&produto=${produto.id}"><img alt="Excluir" src="resources/img/excluir.png" title="Excluir" width="20px" height="20px"></a></td>					
					<td><a href="salvarProduto?acao=editar&produto=${produto.id}"><img alt="Editar" src="resources/img/editar.png" title="Editar" width="20px" height="20px"></a></td>
				</tr>				
			</c:forEach>
	</table>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == "") {
				alert("ATENÇÃO! Informe o nome!");
				return false;
			} else if (document.getElementById("quantidade").value == "") {
				alert("ATENÇÃO! Informe a quantidade!");
				return false;
			} else if (document.getElementById("valor").value == "") {
				alert("ATENÇÃO! Informe o valor!");
				return false;
			}
			return true;
		}
	</script>
	
	<script type="text/javascript">
	    $(function(){
	        $("#valor").maskMoney();
	    })
    </script>
    
    <script type="text/javascript">
		$(function(){
		 $("#demo4").maskMoney({symbol:'R$ ', 
			showSymbol:true, thousands:'.', decimal:',', symbolStay: true});
		 })
	</script>
</body>
</html>