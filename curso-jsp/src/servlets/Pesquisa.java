package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanUsuario;
import dao.DaoUsuario;

/**
 * Servlet implementation class Pesquisa
 */
@WebServlet("/pesquisa")
public class Pesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String descricaoConsulta = request.getParameter("descricaoconsulta");
		
		if (descricaoConsulta != null && !descricaoConsulta.trim().isEmpty()) {
			try {
				List<BeanUsuario> listaPesquisa = daoUsuario.listar(descricaoConsulta);
				if (!listaPesquisa.isEmpty()) {
					RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
					request.setAttribute("usuarios", listaPesquisa);
					view.forward(request, response);
				} else {
					request.setAttribute("msg", "Usuário não encontrado!");
					RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
					view.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
