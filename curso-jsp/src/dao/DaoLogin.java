package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.BeanUsuario;
import connection.SingleConnection;

public class DaoLogin {

	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception {
		String sql = "select * from usuario where login = '" + login + "' and senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String buscarNome(String login) {
		try {
			String sql = "select nome from usuario where login = '" + login + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				BeanUsuario beanUsuario = new BeanUsuario();
				String nome = resultSet.getString("nome");
				beanUsuario.setNome(nome);
				
				return beanUsuario.getNome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
