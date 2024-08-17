package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String drivername = "org.postgresql.Driver";
		String servername = "localhost";
		String mydatabase = "exerciciodois";
		int porta = 5432;
		String url = "jdbc:postgresql://" + servername + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;
		try {
			Class.forName(drivername);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch(ClassNotFoundException e){
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch(SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}
		
		return status;
	}
	
	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return status;
	}
	
	public boolean inserirproduto(Estoque estoque) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO estoque (codigo, produto, valor, quantidade) " 
							+ "VALUES ("+estoque.getcodigo()+ ", '" + estoque.getproduto() + "', "
							+ estoque.getvalor() + ", " + estoque.getquantidade()
							+ ");");
			st.close();
			status = true;
		}catch(SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarproduto(Estoque estoque) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE estoque SET produto = '" + estoque.getproduto() 
			+ "', valor = " + estoque.getvalor() + ", quantidade = " + estoque.getquantidade() 
			+ " WHERE codigo = " + estoque.getcodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
			}catch(SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirproduto(int codigo) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM estoque WHERE codigo = " + codigo);
			st.close();
			status = true;
		}catch(SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Estoque[] getestoque() {
		Estoque[] estoquetotal = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM estoque");
			if(rs.next()) {
				rs.last();
				estoquetotal = new Estoque[rs.getRow()];
				rs.beforeFirst();
				
				for(int i = 0; rs.next(); i++) {
					estoquetotal[i] = new Estoque(rs.getInt("codigo"), rs.getString("produto"),
							rs.getDouble("valor"), rs.getInt("quantidade"));
				}
			}
			st.close();
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return estoquetotal;
	}
}

