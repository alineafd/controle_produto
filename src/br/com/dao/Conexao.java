package br.com.dao;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.com.pacote1.Produto;

public class Conexao {
	
	Connection con = null;
	public Conexao(){
	}
	
	public void conectar(){
		try{
			Class.forName("org.postgresql.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/supermercado", "postgres", "line1234");
			System.out.println("Conexao realizada com sucesso");
		}
		
		catch(ClassNotFoundException e){
			System.err.print(e.getMessage());
		}
		
		catch(SQLException ex){
			System.err.print(ex.getMessage());
		}
	}
	
	public void adicionarProduto(Produto produto) throws SQLException{
		String sql 			   = "insert into produto (nome, codigo, preco, quantidade) values (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, produto.getNome());
		stmt.setString(2, produto.getCodigo());
		stmt.setDouble(3, produto.getPreco());
		stmt.setInt   (4, produto.getQuantidade());
		
		stmt.execute();
		stmt.close();
	}
	
	public void deletarProduto(Produto produto) throws SQLException{
		String 			  sql = "delete from produto where codigo=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, produto.getCodigo());
		stmt.execute();
		stmt.close();
	}
	
	
	public void mostrarProduto(Produto produto) throws SQLException{
		String            sql  = "select * from produto where codigo = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, produto.getCodigo());
		ResultSet         rs   = stmt.executeQuery();
		
		JFrame    janela;
		JLabel    imprime;
		janela = new JFrame("Mostra Produto");
		
		while(rs.next()){
			String nome    	  = rs.getString("nome");
			String codigo 	  = rs.getString("codigo");
			float  preco  	  = rs.getFloat("preco");
			int    quantidade = rs.getInt("quantidade");
			imprime       = new JLabel();
			imprime.setText("<html>Nome: "+nome+"<br>Codigo: "+codigo+"<br>Preço: R$"+preco+"<br>Quantidade de produtos no estoque: "+quantidade+" unidades<br></html>");
			
			janela.add(imprime);
					
		}
		
		janela.setSize(350, 350);
		janela.setLayout(new FlowLayout());
		janela.setLocationRelativeTo(null);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setVisible(true);
		rs.close();
		stmt.close();
	}	
}
