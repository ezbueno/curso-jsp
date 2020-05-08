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
			String sql = "insert into usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge, "
					+ "fotobase64, contenttype, curriculobase64, contenttypecurriculo, fotoMiniaturaBase64, ativo, sexo, perfil) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			insert.setBoolean(16, beanUsuario.isAtivo());
			insert.setString(17, beanUsuario.getSexo());
			insert.setString(18,  beanUsuario.getPerfil());
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
	
	public List<BeanUsuario> listar(String descricaoConsulta) throws SQLException {
		String sql = "select * from usuario where login <> 'admin' and nome ilike '%" + descricaoConsulta + "%'";
		return consultarUsuarios(sql);
	}
	
	public List<BeanUsuario> listar() throws Exception {		
		String sql = "select * from usuario where login <> 'admin'";
		return consultarUsuarios(sql);
	}

	private List<BeanUsuario> consultarUsuarios(String sql) throws SQLException {
		List<BeanUsuario> listarUsuario = new ArrayList<BeanUsuario>();
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
			//beanUsuario.setFotoBase64(resultSet.getString("fotobase64"));
			beanUsuario.setFotoMiniaturaBase64(resultSet.getString("fotoMiniaturaBase64"));
			beanUsuario.setContentType(resultSet.getString("contenttype"));
			beanUsuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanUsuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanUsuario.setAtivo(resultSet.getBoolean("ativo"));
			beanUsuario.setSexo(resultSet.getString("sexo"));
			beanUsuario.setPerfil(resultSet.getString("perfil"));
			listarUsuario.add(beanUsuario);
		}
		return listarUsuario;
	}
	
	public void deletar(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
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
		String sql = "select * from usuario where id  = '" + id + "' and login <> 'admin'";
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
			beanUsuario.setFotoMiniaturaBase64(resultSet.getString("fotoMiniaturaBase64"));
			beanUsuario.setContentType(resultSet.getString("contenttype"));
			beanUsuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanUsuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanUsuario.setAtivo(resultSet.getBoolean("ativo"));
			beanUsuario.setSexo(resultSet.getString("sexo"));
			beanUsuario.setPerfil(resultSet.getString("perfil"));
			
			return beanUsuario;
		}
		return null;
	}

	public void atualizar(BeanUsuario beanUsuario) {
		try {
			StringBuilder sql = new StringBuilder();
				sql.append(" update usuario set login = ? ");
				sql.append(", senha = ?, nome = ?, telefone = ? ");
				sql.append(", cep = ?, rua = ?, bairro = ? ");
				sql.append(", cidade = ?, estado = ?, ibge = ? , ativo = ?, sexo = ? , perfil = ? ");
				
				if (beanUsuario.isAtualizarImagem()) {
					sql.append(", fotobase64 = ?, contenttype = ? ");					
				}
				
				if (beanUsuario.isAtualizarPDF()) {
					sql.append(", curriculobase64 = ?, contenttypecurriculo = ? ");
				}
				
				if (beanUsuario.isAtualizarImagem()) {
					sql.append(", fotoMiniaturaBase64 = ? ");
				}
				
				sql.append(" where id = " + beanUsuario.getId());
			
				 PreparedStatement update = connection.prepareStatement(sql.toString());
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
				 update.setBoolean(11, beanUsuario.isAtivo());
				 update.setString(12, beanUsuario.getSexo());
				 update.setString(13, beanUsuario.getPerfil());
				 
				 if (beanUsuario.isAtualizarImagem()) {
					 update.setString(14, beanUsuario.getFotoBase64());
					 update.setString(15, beanUsuario.getContentType());				 
				 }
				 
				 if (beanUsuario.isAtualizarPDF() && !beanUsuario.isAtualizarImagem()) {
					 update.setString(14, beanUsuario.getCurriculoBase64());
					 update.setString(15, beanUsuario.getContentTypeCurriculo());					 
				 } 
				 
				 if (beanUsuario.isAtualizarImagem() && beanUsuario.isAtualizarPDF()) {
					 update.setString(16, beanUsuario.getCurriculoBase64());
					 update.setString(17, beanUsuario.getContentTypeCurriculo());
				 }
				 
				 if (beanUsuario.isAtualizarImagem() && !beanUsuario.isAtualizarPDF()) {
					 update.setString(16, beanUsuario.getFotoMiniaturaBase64());
				 } else if (beanUsuario.isAtualizarImagem()) {
					 update.setString(18, beanUsuario.getFotoMiniaturaBase64());
				 }
				 
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
