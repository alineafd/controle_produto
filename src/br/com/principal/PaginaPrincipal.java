package br.com.principal;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import br.com.dao.Conexao;
import br.com.pacote1.Produto;

public class PaginaPrincipal{

	public static void main(String[] args) throws SQLException{
		final  Conexao 			con;
		final  Produto       	p;
		final  JFrame        	janela;
		final  JFrame        	janela2;
		final  JFrame        	janela3;
		final  JFrame           janela4;
		final  JTextField    	nomeProduto;
		final  JTextField    	codigoProduto; 
		final  JTextField    	precoProduto;
		final  JTextField    	quantidadeProduto;
		final  JTextField    	codigoProduto2;
		final  JTextField       codigoProdutoText;
		final  JButton       	botaoOk;
		final  JButton          botaoOk2;
		final  JButton       	botaoDeletar;
		final  JLabel        	nomeProdutoLabel;
		final  JLabel        	codigoProdutoLabel;
		final  JLabel        	precoProdutoLabel;
		final  JLabel        	codigoProdutoLabel2;
		final  JLabel        	quantidadeProdutoLabel;
		final  JLabel        	opcao;
		final  JLabel           codigoProdutoLabel3;
		final  JButton       	adicionaProduto;
		final  JButton       	deletaProduto;
		final  JButton      	verificaProduto;
	    
		p      	               = new Produto();
		con    		           = new Conexao();
		janela 		           = new JFrame("Controle de Produtos");
		botaoOk 	           = new JButton("Ok");
		nomeProdutoLabel       = new JLabel("Nome do Produto:");
		codigoProdutoLabel     = new JLabel("Código do Produto:");
		precoProdutoLabel  	   = new JLabel("Preço do Produto:");
		quantidadeProdutoLabel = new JLabel("Quantidade de produtos no estoque:");
		janela2 	           = new JFrame();
		nomeProduto            = new JTextField(20);
		codigoProduto          = new JTextField(20);
		precoProduto           = new JTextField(20);
		quantidadeProduto      = new JTextField(20);
		adicionaProduto        = new JButton("Cadastrar Produto");
		deletaProduto          = new JButton("Deletar Produto");
		verificaProduto        = new JButton("Verificar Produto");
		opcao                  = new JLabel("O que deseja fazer?");
		
		adicionaProduto.setPreferredSize(new Dimension(150,30));
		deletaProduto.setPreferredSize(new Dimension(150,30));
		verificaProduto.setPreferredSize(new Dimension(150,30));
		//janela.getContentPane().setBackground(Color.WHITE);
		janela.add(opcao);
		janela.add(adicionaProduto);
		janela.add(deletaProduto);
		janela.add(verificaProduto);
		
		adicionaProduto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				janela2.setSize(260, 290);
				janela2.setTitle("Cadastrar Produto");
				janela2.add(nomeProdutoLabel);
				janela2.add(nomeProduto);
				janela2.add(codigoProdutoLabel);
				janela2.add(codigoProduto);
				janela2.add(precoProdutoLabel);
				janela2.add(precoProduto);
				janela2.add(quantidadeProdutoLabel);
				janela2.add(quantidadeProduto);
				botaoOk.setBounds(40, 40, 200, 40);
				janela2.add(botaoOk);
				
				botaoOk.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						p.setNome(nomeProduto.getText());
						p.setCodigo(codigoProduto.getText());
						p.setPreco(Float.parseFloat(precoProduto.getText()));
						p.setQuantidade(Integer.parseInt(quantidadeProduto.getText()));
						try {
							con.adicionarProduto(p);
							JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
				
				janela2.setLayout(new FlowLayout());
				janela2.setLocationRelativeTo(null);
				janela2.setVisible(true);
				janela2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			}
		});
		
		janela3 		    = new JFrame();
		codigoProdutoLabel2 = new JLabel("Codigo do produto que será deletado:");
		codigoProduto2      = new JTextField(20);
		botaoDeletar        = new JButton("Deletar");
		
			
		deletaProduto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				janela3.add(codigoProdutoLabel2);
				janela3.add(codigoProduto2);
				janela3.add(botaoDeletar);
				
				botaoDeletar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						p.setCodigo(codigoProduto2.getText());
						try {
							con.deletarProduto(p);
							JOptionPane.showMessageDialog(null, "Produto deletado com sucesso");
						} catch (SQLException e) {
							System.out.println("Nao foi possivel deletar o produto na tabela");
						}
					}
				});
				
				janela3.setSize(500, 100);
				janela3.setTitle("Deletar Produto");
				janela3.setLayout(new FlowLayout());
				janela3.setLocationRelativeTo(null);
				janela3.setVisible(true);
				janela3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			}
		});

		botaoOk2            = new JButton("Ok");
		janela4             = new JFrame();
		codigoProdutoLabel3 = new JLabel("Digite o codigo do produto"); 
		codigoProdutoText   = new JTextField(20);
		
		
		verificaProduto.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent event){
				janela4.add(codigoProdutoLabel3);
				janela4.add(codigoProdutoText);
				janela4.add(botaoOk2);
				
				botaoOk2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						p.setCodigo(codigoProdutoText.getText());
						try {
							con.mostrarProduto(p);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
								
				janela4.setSize(500, 100);
				janela4.setTitle("Mostrar Produto");
				janela4.setLayout(new FlowLayout());
				janela4.setLocationRelativeTo(null);
				janela4.setVisible(true);
				janela4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
		});
		
		
		con.conectar();
		janela.setSize(270, 250);
		janela.setLayout(new FlowLayout());
		janela.setLocationRelativeTo(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		
		
		
		
	}
}
