<%@page import="beans.BeanUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Usuários</title>
	<link rel="stylesheet" href="resources/css/cadastro.css">
	
	    <!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
	
</head>
<body>
	<header class="header">
		<a href="acessoliberado.jsp"><img alt="Início" src="resources/img/home.png" width="30px" height="30px" id="botao-inicio"></a>
		<a href="index.jsp"><img alt="Sair" src="resources/img/logout.png" width="30px" height="30px" onclick="return confirm('Deseja sair do sistema?')" id="botao-sair"></a>
	</header>
	<h1>Cadastro de Usuários</h1>
	<h3>${msg}</h3>
	<h3 id="msgSalvarAtualizarExcluir">${msgSalvarAtualizarExcluir}</h3>
	<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false" enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table class="minhatabela">
					<tr>
						<td>Código:</td>
						<c:if test="${user.id == null}">
							<td><input type="text" readonly="readonly" id="id" name="id" style="background-color: #DCDCDC" onclick="alert('Campo de preenchimento automático.')"
								style="width: 300px" value="${user.id}" class="field-long"></td>
						</c:if>
						
						<c:if test="${user.id != null}">
							<td><input type="text" readonly="readonly" id="id" name="id" style="background-color: #DCDCDC" onclick="alert('Não é permitido alterar o código do usuário.')"
								style="width: 300px" value="${user.id}" class="field-long"></td>
						</c:if>
							
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							style="width: 300px" value="${user.cep}" maxlength="9" class="field-long"></td>	
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							style="width: 300px" value="${user.login}" maxlength="10" class="field-long"></td>
							
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							style="width: 300px" value="${user.rua}" maxlength="50" class="field-long"></td>	
					</tr>
					
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							style="width: 300px" value="${user.senha}" maxlength="10" class="field-long"></td>
							
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							style="width: 300px" value="${user.bairro}" maxlength="50" class="field-long"></td>	
					</tr>
					 
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							style="width: 300px" value="${user.nome}" maxlength="50" class="field-long"></td>
							
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							style="width: 300px" value="${user.cidade}" maxlength="50" class="field-long"></td>	
					</tr>					
					
					<tr>
					<!-- <td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone"
							value="${user.telefone}" class="field-long"></td> -->
							
						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"
							style="width: 300px" value="${user.estado}" maxlength="50" class="field-long"></td>	
							
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							style="width: 300px" value="${user.ibge}" maxlength="20" class="field-long"></td>
					</tr>
															
					<tr>					
						<td>Foto:</td>
						<td><input type="file" name="foto" style="width: 300px">
						<!-- <input type="text" style="display: none;" readonly="readonly" name="fotoTemp" value="${user.fotoBase64}">
							<input type="text" style="display: none;" readonly="readonly" name="contentTypeTemp" value="${user.contentType}"> -->
						
						<td>Currículo:</td>
						<td><input type="file" name="curriculo" style="width: 300px">
							<!-- <input type="text" style="display: none;" readonly="readonly" name="curriculoTemp" value="${user.curriculoBase64}">
							<input type="text" style="display: none;" readonly="readonly" name="contentTypeCurriculoTemp" value="${user.contentTypeCurriculo}"> -->
						</td>						
					</tr>													
				</table>
				
				
				<table>
					<tr>
						<td id="user-ativo" style="width: 53px">Ativo:</td>
						<td><input type="checkbox" name="ativo" id="ativo" ${user.ativo ? 'checked' : ''} />
						
												
						<td style="width: 108px"></td>
						<td id="sexo-user" style="width: 67px">Sexo:</td>
						<td>
							<input type="radio" name="sexo" value="masculino" ${user.sexo eq 'masculino' ? 'checked' : ''} />Masculino
							<input type="radio" name="sexo" value="feminino" ${user.sexo eq 'feminino' ? 'checked' : ''} />Feminino
						</td>
							<%-- Outra maneira de comparar o sexo: 
								if (request.getAttribute("user") != null) {
									BeanUsuario beanUsuario = (BeanUsuario) request.getAttribute("user");
									if (beanUsuario.getSexo().equalsIgnoreCase("masculino")) {
										out.print(" ");
										out.print("checked=\"checked\"");
										out.print(" ");
									}
								}
							--%>
							
						<td style="width: 103px"></td>
						<td id="perfil-user" style="width: 67px">Perfil:</td>
						<td>
							<select id="perfil" name="perfil">
								<option value="nao-informado">[--Selecione--]</option>
								<option value="administrador" ${user.perfil eq 'administrador' ? 'selected' : ''}>Administrador(a)</option> 
								<option value="diretor" ${user.perfil eq 'diretor' ? 'selected' : ''}>Diretor(a)</option>
								<option value="gerente" ${user.perfil eq 'gerente' ? 'selected' : ''}>Gerente</option>
								<option value="secretario" ${user.perfil eq 'secretario' ? 'selected' : ''}>Secretário(a)</option>
							</select>
						</td>			
					</tr>
				</table>
				
				<br>
				
				<div id="botao">
					<input type="submit" value="Salvar" class="botaoSalvar" />
					<input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'" class="botaoCancelar" />
				</div>
			</li>
		</ul>
	</form>
	
	<form action="pesquisa" method="post">
		<ul class="form-style-1">
				<li>
					<table class="">
						<tr>
							<td style="width: 160px"></td>
							<td><strong>Descrição:</strong></td>
							<td><input type="text" id="descricaoconsulta" name="descricaoconsulta" style="width: 300px" /></td>
							<td class="form-style-2"><input type="submit" value="Pesquisar" class="botaoPesquisar" /></td>
						</tr>						
					</table>
				</li>	
		</ul>			
	</form>	
	
	<table class="tabela-usuarios">
	<caption><strong>Lista de Usuários</strong></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Usuário</th>
				<th>Foto</th>
				<th>Currículo</th>
				<th>Nome</th>
				<th>Telefones</th>
				<!-- th>Telefone</th> -->
				<th>Cep</th>
				<th>Rua</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>IBGE</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 280px"><c:out value="${user.id}"></c:out></td>
					<td style="width: 280px"><c:out value="${user.login}"></c:out></td>
					
					<c:if test="${user.fotoMiniaturaBase64 != null}">
						<td style="width: 280px"><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img src='<c:out value="${user.fotoMiniaturaBase64}"></c:out>' alt="Imagem" title="Foto do Usuário" width="30px" height="30px"></a></td>
					</c:if>
					
					<c:if test="${user.fotoMiniaturaBase64 == null}">
						<td style="width: 280px"><img src="resources/img/no-user.png" alt="Imagem" title="Usuário sem foto" width="30px" height="30px"></td>						
					</c:if>
					
					<c:if test="${user.curriculoBase64 != null}">
						<td style="width: 280px"><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img src="resources/img/pdf.png" alt="Currículo" title="Currículo do usuário" width="30px" height="30px"></a></td>
					</c:if>
					
					<c:if test="${user.curriculoBase64 == null}">
						<td style="width: 280px"><img src="resources/img/no-pdf.png" alt="Currículo" title="Usuário sem currículo" width="30px" height="30px"></td>
					</c:if>
										
					<td style="width: 280px"><c:out value="${user.nome}"></c:out>
					<td style="width: 280px"><a href="salvarTelefone?acao=addFone&user=${user.id}"><img alt="Telefones" src="resources/img/fone.png" title="Telefones" width="30px" height="30px"></a></td>
					<!--  <td style="width: 280px"><c:out value="${user.telefone}"></c:out> -->
					<td style="width: 280px"><c:out value="${user.cep}"></c:out>
					<td style="width: 280px"><c:out value="${user.rua}"></c:out>
					<td style="width: 280px"><c:out value="${user.bairro}"></c:out>
					<td style="width: 280px"><c:out value="${user.cidade}"></c:out>
					<td style="width: 280px"><c:out value="${user.estado}"></c:out>
					<td style="width: 280px"><c:out value="${user.ibge}"></c:out>
					<td style="width: 280px"><a href="salvarUsuario?acao=delete&user=${user.id}"><img alt="Excluir" src="resources/img/excluir.png" title="Excluir" width="22px" height="22px" onclick=" return confirm('Deseja realizar a exclusão?')"></a></td>
					<td style="width: 280px"><a href="salvarUsuario?acao=editar&user=${user.id}"><img alt="Editar" src="resources/img/editar.png" title="Editar" width="22px" height="22px"></a></td>
				</tr>
			</c:forEach>
	</table>	
		<script type="text/javascript">
			function validarCampos() {
				if (document.getElementById("login").value == "") {
					alert("ATENÇÃO! Informe o login!");
					return false;
				} else if (document.getElementById("senha").value == "") {
					alert("ATENÇÃO! Informe a senha!");
					return false;
				} else if (document.getElementById("nome").value == "") {
					alert("ATENÇÃO! Informe o nome!");
					return false;
				} else if (document.getElementById("telefone").value == "") {
					alert("ATENÇÃO! Informe o telefone!");
					return false;
				}
				return true;
			}
			
			$(document).ready(function() {
	
	            function limpa_formulário_cep() {
	                // Limpa valores do formulário de cep.
	                $("#rua").val("");
	                $("#bairro").val("");
	                $("#cidade").val("");
	                $("#estado").val("");
	                $("#ibge").val("");
	            }
	            
	            //Quando o campo cep perde o foco.
	            $("#cep").blur(function() {
	
	                //Nova variável "cep" somente com dígitos.
	                var cep = $(this).val().replace(/\D/g, '');
	
	                //Verifica se campo cep possui valor informado.
	                if (cep != "") {
	
	                    //Expressão regular para validar o CEP.
	                    var validacep = /^[0-9]{8}$/;
	
	                    //Valida o formato do CEP.
	                    if(validacep.test(cep)) {
	
	                        //Preenche os campos com "..." enquanto consulta webservice.
	                        $("#rua").val("...");
	                        $("#bairro").val("...");
	                        $("#cidade").val("...");
	                        $("#estado").val("...");
	                        $("#ibge").val("...");
	
	                        //Consulta o webservice viacep.com.br/
	                        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
	
	                            if (!("erro" in dados)) {
	                                //Atualiza os campos com os valores da consulta.
	                                $("#rua").val(dados.logradouro);
	                                $("#bairro").val(dados.bairro);
	                                $("#cidade").val(dados.localidade);
	                                $("#estado").val(dados.uf);
	                                $("#ibge").val(dados.ibge);
	                            } //end if.
	                            else {
	                                //CEP pesquisado não foi encontrado.
	                                limpa_formulário_cep();
	                                alert("CEP não encontrado.");
	                            }
	                        });
	                    } //end if.
	                    else {
	                        //cep é inválido.
	                        limpa_formulário_cep();
	                        alert("Formato de CEP inválido.");
	                    }
	                } //end if.
	                else {
	                    //cep sem valor, limpa formulário.
	                    limpa_formulário_cep();
	                }
	            });
	        });
		</script>
</body>
</html>