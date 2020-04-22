package servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.BeanProduto;
import dao.DaoProduto;


/**
 * Servlet implementation class Produto
 */
@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private DaoProduto daoProduto = new DaoProduto();
	
    public Produto() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");
			
			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.deletar(produto);
				request.setAttribute("msgSalvarAtualizarExcluir", "Produto excluído com sucesso!");
		
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanProduto beanProduto = daoProduto.consultar(produto);
								
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", beanProduto);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodosprodutos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
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
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			beanProduto.setNome(nome);
			
			if (quantidade != null && !quantidade.isEmpty() ) {
				beanProduto.setQuantidade(Double.parseDouble(quantidade));
			}
			
			if (valor != null && !valor.isEmpty()) {
				beanProduto.setValor(Double.parseDouble(valor.replaceAll("\\,", ".")));
			}
			
			try {
				boolean insere = true;
				if (nome == null || nome.isEmpty()) {
					request.setAttribute("msg", "ATENÇÃO! O nome deve ser informado!");
					insere = false;
				} else if (quantidade == null || quantidade.isEmpty()) {
					request.setAttribute("msg", "ATENÇÃO! A quantidade deve ser informada!");
					insere = false;
				} else if (valor == null || valor.isEmpty()) {
					request.setAttribute("msg", "ATENÇÃO! O valor deve ser informado!");
					insere = false;
				} else if (id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {
					request.setAttribute("msg", "ATENÇÃO! Já existe um produto com o mesmo nome!");
					insere = false;
				} else if (id == null || id.isEmpty() && daoProduto.validarNome(nome)) {
					daoProduto.salvar(beanProduto);
					request.setAttribute("msgSalvarAtualizarExcluir", "Produto cadastrado com sucesso!");
				} else if (id != null && !id.isEmpty() && !daoProduto.validarNome(nome)) {
					request.setAttribute("msg", "ATENÇÃO! Já existe um produto com o mesmo nome!");
					insere = false;
				} else if (id != null && !id.isEmpty() && daoProduto.validarNome(nome)) {
					daoProduto.atualizar(beanProduto);
					request.setAttribute("msgSalvarAtualizarExcluir", "Produto editado com sucesso!");
				}
				
				if (!insere) {
					request.setAttribute("produto", beanProduto);
				}
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
