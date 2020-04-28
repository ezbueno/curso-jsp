package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanUsuario;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanUsuario beanUsuario) {
		try {
			String sql = "insert into usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge, fotobase64, contenttype, curriculobase64, contenttypecurriculo, fotoMiniaturaBase64) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, beanUsuario.getLogin());
			insert.setString(2, beanUsuario.getSenha());
			insert.setString(3, beanUsuario.getNome());
			insert.setString(4, beanUsuario.getTelefone());
			insert.setString(5, beanUsuario.getCep());
			insert.setString(6, beanUsuario.getRua());
			insert.setString(7, beanUsuario.getBairro());
			insert.setString(8, beanUsuario.getCidade());
			insert.setString(9, beanUsuario.getEstado());
			insert.setString(10, beanUsuario.getIbge());
			insert.setString(11, beanUsuario.getFotoBase64());
			insert.setString(12, beanUsuario.getContentType());
			insert.setString(13, beanUsuario.getCurriculoBase64());
			insert.setString(14, beanUsuario.getContentTypeCurriculo());
			insert.setString(15, beanUsuario.getFotoMiniaturaBase64());
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
	
	public List<BeanUsuario> listar()  throws Exception {
		List<BeanUsuario> listarUsuario = new ArrayList<BeanUsuario>();
		
		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			BeanUsuario beanUsuario = new BeanUsuario();
			beanUsuario.setId(resultSet.getLong("id"));
			beanUsuario.setLogin(resultSet.getString("login"));
			beanUsuario.setSenha(resultSet.getString("senha"));
			beanUsuario.setNome(resultSet.getString("nome"));
			beanUsuario.setTelefone(resultSet.getString("telefone"));
			beanUsuario.setCep(resultSet.getString("cep"));
			beanUsuario.setRua(resultSet.getString("rua"));
			beanUsuario.setBairro(resultSet.getString("bairro"));
			beanUsuario.setCidade(resultSet.getString("cidade"));
			beanUsuario.setEstado(resultSet.getString("estado"));
			beanUsuario.setIbge(resultSet.getString("ibge"));
			beanUsuario.setFotoBase64(resultSet.getString("fotobase64"));
			beanUsuario.setContentType(resultSet.getString("contenttype"));
			beanUsuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanUsuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			listarUsuario.add(beanUsuario);
		}
		return listarUsuario;
	}
	
	public void deletar(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "'";
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
	
	public BeanUsuario consultar(String id) throws Exception {
		String sql = "select * from usuario where id  = '" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			BeanUsuario beanUsuario = new BeanUsuario();
			beanUsuario.setId(resultSet.getLong("id"));
			beanUsuario.setLogin(resultSet.getString("login"));
			beanUsuario.setSenha(resultSet.getString("senha"));
			beanUsuario.setNome(resultSet.getString("nome"));
			beanUsuario.setTelefone(resultSet.getString("telefone"));
			beanUsuario.setCep(resultSet.getString("cep"));
			beanUsuario.setRua(resultSet.getString("rua"));
			beanUsuario.setBairro(resultSet.getString("bairro"));
			beanUsuario.setCidade(resultSet.getString("cidade"));
			beanUsuario.setEstado(resultSet.getString("estado"));
			beanUsuario.setIbge(resultSet.getString("ibge"));
			beanUsuario.setFotoBase64(resultSet.getString("fotobase64"));
			beanUsuario.setContentType(resultSet.getString("contenttype"));
			beanUsuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanUsuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			
			return beanUsuario;
		}
		return null;
	}

	public void atualizar(BeanUsuario beanUsuario) {
		try {
			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ?, fotobase64 = ?, contenttype = ?, curriculobase64 = ?, contenttypecurriculo = ?, fotoMiniaturaBase64 = ? where id = " + beanUsuario.getId();
			 PreparedStatement update = connection.prepareStatement(sql);
			 update.setString(1, beanUsuario.getLogin());
			 update.setString(2, beanUsuario.getSenha());
			 update.setString(3, beanUsuario.getNome());
			 update.setString(4, beanUsuario.getTelefone());
			 update.setString(5, beanUsuario.getCep());
			 update.setString(6, beanUsuario.getRua());
			 update.setString(7, beanUsuario.getBairro());
			 update.setString(8, beanUsuario.getCidade());
			 update.setString(9, beanUsuario.getEstado());
			 update.setString(10, beanUsuario.getIbge());
			 update.setString(11, beanUsuario.getFotoBase64());
			 update.setString(12, beanUsuario.getContentType());
			 update.setString(13, beanUsuario.getCurriculoBase64());
			 update.setString(14, beanUsuario.getContentTypeCurriculo());
			 update.setString(15, beanUsuario.getFotoMiniaturaBase64());
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
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login  = '" + login + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}
	
	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where login  = '" + login + "' and id <> " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}
	
	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}
	
	public boolean validarSenhaUpdate(String senha, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha  = '" + senha + "' and id <> " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}
}
