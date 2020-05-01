package servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/*
import java.util.ArrayList;
import java.util.List;
*/

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

/*
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
*/

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

/*
import org.apache.tomcat.util.codec.binary.Base64;
*/

import beans.BeanUsuario;
import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private DaoUsuario daoUsuario = new DaoUsuario();
	
    public Usuario() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {			
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			String tipo = request.getParameter("tipo");
			
			if (acao != null && acao.equalsIgnoreCase("delete")) {
				daoUsuario.deletar(user);
				request.setAttribute("msgSalvarAtualizarExcluir", "Usuário excluído com sucesso!");
		
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("editar")) {
				BeanUsuario beanUsuario = daoUsuario.consultar(user);
								
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanUsuario);
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("download")) {
				BeanUsuario beanUsuario = daoUsuario.consultar(user);
				if (beanUsuario != null) {
					
					
					if (tipo.equalsIgnoreCase("imagem")) {
						response.setHeader("Content-Disposition", "attachment;filename=imagem." + beanUsuario.getContentType().split("\\/")[1]);
						
						new Base64();
						/* Converte a base64 da imagem no banco para byte[] */
						byte[] imagemFotoBytes = Base64.decodeBase64(beanUsuario.getFotoBase64());
						
						/* Coloca os bytes em um objeto de entrada para processar */
						InputStream inputStream = new ByteArrayInputStream(imagemFotoBytes);
						
						/* Início da resposta para o navegador */
						int read = 0;
						byte[] bytes = new byte[1024];
						OutputStream outputStream = response.getOutputStream();
						
						while((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
						
						outputStream.flush();
						outputStream.close();
					} else if (tipo.equalsIgnoreCase("curriculo")) {
						response.setHeader("Content-Disposition", "attachment;filename=curriculo." + beanUsuario.getContentTypeCurriculo().split("\\/")[1]);
						
						new Base64();
						/* Converte a base64 da imagem no banco para byte[] */
						byte[] curriculoBytes = Base64.decodeBase64(beanUsuario.getCurriculoBase64());
						
						/* Coloca os bytes em um objeto de entrada para processar */
						InputStream inputStream = new ByteArrayInputStream(curriculoBytes);
						
						/* Início da resposta para o navegador */
						int read = 0;
						byte[] bytes = new byte[1024];
						OutputStream outputStream = response.getOutputStream();
						
						while((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
						
						outputStream.flush();
						outputStream.close();
					}
				}
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		String acao = request.getParameter("acao");
		
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");
			
			BeanUsuario beanUsuario = new BeanUsuario();
			beanUsuario.setId((id != null && !id.isEmpty()) ? Long.parseLong(id) : null);
			beanUsuario.setLogin(login);
			beanUsuario.setSenha(senha);
			beanUsuario.setNome(nome);
			beanUsuario.setTelefone(telefone);
			beanUsuario.setCep(cep);
			beanUsuario.setRua(rua);
			beanUsuario.setBairro(bairro);
			beanUsuario.setCidade(cidade);
			beanUsuario.setEstado(estado);
			beanUsuario.setIbge(ibge);

			boolean insere = true;
			
			try {
				/* Início - File Upload de imagens e PDF */
				
				if (ServletFileUpload.isMultipartContent(request)) {
					
					/*
					List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					
					for (FileItem fileItem : fileItems) {
						if (fileItem.getFieldName().equals("foto")) {
							new Base64();
							String fotoBase64 = Base64.encodeBase64String(fileItem.get());
							String contentType = fileItem.getContentType();
							beanUsuario.setFotoBase64(fotoBase64);
							beanUsuario.setContentType(contentType);
						}
					}
					*/
					
					Part imagemFoto = request.getPart("foto");
				
					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {						
						new Base64();
						String fotoBase64 = Base64.encodeBase64String(converterStreamParaByte(imagemFoto.getInputStream()));
						
						beanUsuario.setFotoBase64(fotoBase64);
						beanUsuario.setContentType(imagemFoto.getContentType());
						
						/* Início - Imagem em miniatura */
						
						/* Transforma em um BufferedImage */
						new Base64();
						byte[] imageByteDecode = Base64.decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						
						/* Pega o tipo da imagem */
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
						
						/* Cria a imagem em miniatura */
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D graphics2d = resizedImage.createGraphics();
						graphics2d.drawImage(bufferedImage, 0, 0, 100, 100, null);
						graphics2d.dispose();
						
						/* Escreve a imagem novamente */
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", byteArrayOutputStream);
						
						String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(byteArrayOutputStream.toByteArray());
						
						beanUsuario.setFotoMiniaturaBase64(miniaturaBase64);
						
						/* Fim - Imagem em miniatura */
						
					} else {
						/*
						beanUsuario.setFotoBase64(request.getParameter("fotoTemp"));
						beanUsuario.setContentType(request.getParameter("contentTypeTemp"));
						*/
						beanUsuario.setAtualizarImagem(false);
					}
					
					Part curriculo = request.getPart("curriculo");
					
					if (curriculo != null && curriculo.getInputStream().available() > 0) {
						new Base64();
						String curriculoBase64 = Base64.encodeBase64String(converterStreamParaByte(curriculo.getInputStream()));
						
						beanUsuario.setCurriculoBase64(curriculoBase64);
						beanUsuario.setContentTypeCurriculo(curriculo.getContentType());
					} else {
						/*
						beanUsuario.setCurriculoBase64(request.getParameter("curriculoTemp"));
						beanUsuario.setContentTypeCurriculo(request.getParameter("contentTypeCurriculoTemp"));
						*/
						beanUsuario.setAtualizarPDF(false);
					}
				}
				/* Fim - File Upload de imagens e PDF */
				
				if (login == null || login.isEmpty()) {
					request.setAttribute("msg", "ATENÇÃO! O login deve ser informado!");
					insere = false;
				} else if (senha == null || senha.isEmpty()) {
					request.setAttribute("msg", "ATENÇÃO! A senha deve ser informada!");
					insere = false;
				} else if (nome == null || nome.isEmpty()) {
					request.setAttribute("msg", "ATENÇÃO! O nome deve ser informado!");
					insere = false;
				/*} else if (telefone == null || telefone.isEmpty()) {
					request.setAttribute("msg", "ATENÇÃO! O telefone deve ser informado!");
					insere = false;*/
				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("msg", "ATENÇÃO! Já existe um usuário com a mesma senha!");
					insere = false;
				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "ATENÇÃO! Já existe um usuário com o mesmo login!");
					insere = false;
				} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login)) {
					if (senha.length() >= 4) {
						daoUsuario.salvar(beanUsuario);
						request.setAttribute("msgSalvarAtualizarExcluir", "Usuário cadastrado com sucesso!");
					} else {
						request.setAttribute("msg", "ATENÇÃO! A senha deve conter no mínimo 4 dígitos!");
						insere = false;
					}
				} else if (id != null && !id.isEmpty()) {
					if (senha.length() >= 4) {
						if (!daoUsuario.validarLoginUpdate(login, id)) {
							request.setAttribute("msg", "ATENÇÃO! Já existe um usuário com o mesmo login!");
							insere = false;
						} else if (!daoUsuario.validarSenhaUpdate(senha, id)) {
							request.setAttribute("msg", "ATENÇÃO! Já existe um usuário com a mesma senha!");
							insere = false;
						} else {
							daoUsuario.atualizar(beanUsuario);
							request.setAttribute("msgSalvarAtualizarExcluir", "Usuário editado com sucesso!");
						}
					} else {
						request.setAttribute("msg", "ATENÇÃO! A senha deve conter no mínimo 4 dígitos!");
						insere = false;
					}
				}
				
				if (!insere) {
					request.setAttribute("user", beanUsuario);
				}
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	private byte[] converterStreamParaByte(InputStream imagem) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int lerStream = imagem.read();
	
		while(lerStream != -1) {
			byteArrayOutputStream.write(lerStream);
			lerStream = imagem.read();
		}
		
		return byteArrayOutputStream.toByteArray();
	}
	
}
