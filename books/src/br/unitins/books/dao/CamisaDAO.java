package br.unitins.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

import br.unitins.books.model.Camisa;

public class CamisaDAO extends DAO<Camisa> {
	
	public boolean create (Camisa camisa) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO camisa ");
		sql.append("	(modelo, marca, cor, tamanho, preco, estoque) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ? , ?, ?, ? ) ");
		
		
	
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, camisa.getModelo());
			stat.setString(2, camisa.getMarca());
			stat.setString(3, camisa.getCor());
			stat.setString(4, camisa.getTamanho());
			if (camisa.getPreco() != null)
				stat.setFloat(5, camisa.getPreco());
			else
				stat.setNull(5, java.sql.Types.FLOAT);
			
			if (camisa.getEstoque() != null)
				stat.setInt(6, camisa.getEstoque());
			else
				stat.setNull(6, java.sql.Types.INTEGER);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclusão realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public boolean update(Camisa camisa) {
		boolean retorno = false;
		Connection conn = getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE camisa ");
		sql.append("	SET modelo=?, marca=?, cor=?, tamanho=?, preco=?, estoque=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, camisa.getModelo());
			stat.setString(2, camisa.getMarca());
			stat.setString(3, camisa.getCor());
			stat.setString(4, camisa.getTamanho());
			stat.setFloat(5, camisa.getPreco());
			stat.setInt(6, camisa.getEstoque());
			stat.setInt(7, camisa.getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Alteração realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;		
		
	}

	public boolean delete(int id) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM camisa ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Remoc�o realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public List<Camisa> findAll() {
		List<Camisa> listaCamisa = new ArrayList<Camisa>();
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	modelo, marca, cor, tamanho, preco, estoque ");
		sql.append("FROM ");
		sql.append("	camisa ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stat.executeQuery();
			
			Camisa camisa = null;
			
			while(rs.next()) {
				camisa = new Camisa();
				camisa.setId(rs.getInt("id"));
				camisa.setModelo(rs.getString("modelo"));
				camisa.setMarca(rs.getString("marca"));
				camisa.setCor(rs.getString("cor"));
				camisa.setTamanho(rs.getString("tamanho"));
				camisa.setPreco(rs.getFloat("preco"));
				camisa.setEstoque(rs.getInt("estoque"));
				listaCamisa.add(camisa);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaCamisa;
	}

	public List<Camisa> findByModelo(String modelo) {
		List<Camisa> listaCamisa = new ArrayList<Camisa>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, modelo, marca, cor, tamanho, preco, estoque ");
		sql.append("FROM ");
		sql.append("	camisa ");
		sql.append("WHERE ");
		sql.append("	modelo ilike ? ");
		sql.append("ORDER BY modelo ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + modelo  + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Camisa camisa = null;
			
			while(rs.next()) {
				camisa = new Camisa();
				camisa.setId(rs.getInt("id"));
				camisa.setModelo(rs.getString("modelo"));
				camisa.setMarca(rs.getString("marca"));
				camisa.setCor(rs.getString("cor"));
				camisa.setTamanho(rs.getString("tamanho"));
				camisa.setPreco(rs.getFloat("preco"));
				camisa.setEstoque(rs.getInt("estoque"));
				listaCamisa.add(camisa);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaCamisa;
	}	
	
	public List<Camisa> findByMarca(String marca) {
		List<Camisa> listaCamisa = new ArrayList<Camisa>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, modelo, marca, cor, tamanho, preco, estoque ");
		sql.append("FROM ");
		sql.append("	camisa ");
		sql.append("WHERE ");
		sql.append("	marca ilike ? ");
		sql.append("ORDER BY  ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + marca  + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Camisa camisa = null;
			
			while(rs.next()) {
				camisa = new Camisa();
				camisa.setId(rs.getInt("id"));
				camisa.setModelo(rs.getString("modelo"));
				camisa.setMarca(rs.getString("marca"));
				camisa.setCor(rs.getString("cor"));
				camisa.setTamanho(rs.getString("tamanho"));
				camisa.setPreco(rs.getFloat("preco"));
				camisa.setEstoque(rs.getInt("estoque"));
				listaCamisa.add(camisa);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaCamisa;
	}	
	
	public Camisa findById(int id) {
		Camisa camisa = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, modelo, marca, cor, tamanho, preco, estoque ");
		sql.append("FROM ");
		sql.append("	camisa ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				camisa = new Camisa();
				camisa.setId(rs.getInt("id"));
				camisa.setModelo(rs.getString("modelo"));
				camisa.setMarca(rs.getString("marca"));
				camisa.setCor(rs.getString("cor"));
				camisa.setTamanho(rs.getString("tamanho"));
				camisa.setPreco(rs.getFloat("preco"));
				camisa.setEstoque(rs.getInt("estoque"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return camisa;
	}

}
