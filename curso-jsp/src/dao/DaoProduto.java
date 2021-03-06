package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanProduto beanProduto) {
		try {
			String sql = "insert into produto(nome, quantidade, valor, categoria_id) values (?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, beanProduto.getNome());
			insert.setDouble(2, beanProduto.getQuantidade());
			insert.setDouble(3, beanProduto.getValor());
			insert.setLong(4, beanProduto.getCategoria_id());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<BeanProduto> listar() throws Exception{
		List<BeanProduto> listarProduto = new ArrayList<BeanProduto>();
		
		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getDouble("quantidade"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			beanProduto.setCategoria_id(resultSet.getLong("categoria_id"));
			listarProduto.add(beanProduto);
		}
		return listarProduto;
	}
	
	public List<BeanCategoria> listarCategorias() throws Exception {
		List<BeanCategoria> listaCategorias = new ArrayList<BeanCategoria>();
		String sql = "select * from categoria";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			BeanCategoria beanCategoria = new BeanCategoria();
			beanCategoria.setId(resultSet.getLong("id"));
			beanCategoria.setNome(resultSet.getString("nome"));
			listaCategorias.add(beanCategoria);
		}
		return listaCategorias;
	}
	
	public boolean validarNome(String nome) throws Exception {
		String sql = "select count(1) as qtd from produto where nome  = '" + nome + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}
	
	public void deletar(String id) {
		try {
			String sql = "delete from produto where id = '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public BeanProduto consultar(String id) throws Exception{
		String sql = "select * from produto where id = '" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getDouble("quantidade"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			beanProduto.setCategoria_id(resultSet.getLong("categoria_id"));
			return beanProduto;
		}
		
		return null;
	}

	public void atualizar(BeanProduto beanProduto) {
		try {
			String sql = " update produto set nome = ?, quantidade = ?, valor = ?, categoria_id = ? where id = " + beanProduto.getId();
			PreparedStatement update = connection.prepareStatement(sql.toString());
			update.setString(1, beanProduto.getNome());
			update.setDouble(2, beanProduto.getQuantidade());
			update.setDouble(3, beanProduto.getValor());
			update.setLong(4, beanProduto.getCategoria_id());
			update.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
