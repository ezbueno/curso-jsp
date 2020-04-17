package servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanTelefone;
import beans.BeanUsuario;
import dao.DaoTelefone;
import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarTelefone")
public class Telefone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();
	
    public Telefone() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			String acao = request.getParameter("acao");
			
				if (acao.equalsIgnoreCase("addFone")) {
					String user = request.getParameter("user");
					BeanUsuario beanUsuario = daoUsuario.consultar(user);
					request.getSession().setAttribute("userEscolhido", beanUsuario);
					request.setAttribute("userEscolhido", beanUsuario);		
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefone.jsp");
					request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
					dispatcher.forward(request, response);
				} else if (acao.equalsIgnoreCase("deleteFone")) {
					String fone = request.getParameter("foneId");
					daoTelefone.deletar(fone);
					
					BeanUsuario beanUsuario = (BeanUsuario) request.getSession().getAttribute("userEscolhido");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefone.jsp");
					request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
					request.setAttribute("msgSalvarAtualizarExcluir", "Telefone excluído com sucesso!");
					dispatcher.forward(request, response);
				} else if (acao.equalsIgnoreCase("listartodos")) {
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
		
		try {
			BeanUsuario beanUsuario = (BeanUsuario) request.getSession().getAttribute("userEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");
				
			BeanTelefone beanTelefone = new BeanTelefone();
			beanTelefone.setNumero(numero);
			if (numero != null && !numero.isEmpty()) {
				beanTelefone.setTipo(tipo);
				beanTelefone.setUsuario(beanUsuario.getId());
				
				daoTelefone.salvar(beanTelefone);
				
				request.getSession().setAttribute("userEscolhido", beanUsuario);
				request.setAttribute("userEscolhido", beanUsuario);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefone.jsp");
				request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
				request.setAttribute("msgSalvarAtualizarExcluir", "Telefone salvo com sucesso!");
				dispatcher.forward(request, response);
			} else {							
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefone.jsp");
				request.setAttribute("msg", "ATENÇÃO! O telefone deve ser informado!");
				request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
