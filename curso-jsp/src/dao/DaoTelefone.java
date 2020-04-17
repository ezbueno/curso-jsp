package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {

	private Connection connection;

	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanTelefone beanTelefone) {
		try {
			String sql = "insert into telefone(numero, tipo, usuario) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, beanTelefone.getNumero());
			insert.setString(2, beanTelefone.getTipo());
			insert.setLong(3, beanTelefone.getUsuario());
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
	
	public List<BeanTelefone> listar(Long usuario) throws Exception{
		List<BeanTelefone> listarTelefone = new ArrayList<BeanTelefone>();
		
		String sql = "select * from telefone where usuario = " + usuario;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			BeanTelefone beanTelefone = new BeanTelefone();
			beanTelefone.setId(resultSet.getLong("id"));
			beanTelefone.setNumero(resultSet.getString("numero"));
			beanTelefone.setTipo(resultSet.getString("tipo"));
			beanTelefone.setUsuario(resultSet.getLong("usuario"));
			listarTelefone.add(beanTelefone);
		}
		return listarTelefone;
	}
		
	public void deletar(String id) {
		try {
			String sql = "delete from telefone where id = '" + id + "'";
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
}
