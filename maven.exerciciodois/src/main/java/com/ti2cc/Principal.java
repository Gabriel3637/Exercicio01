package com.ti2cc;

import java.util.*;

public class Principal {
	//Variavel scanner para ler da entrada padrap
	public static Scanner sc = new Scanner(System.in);
	
	/*
	 * Indentificacao: listar()
	 * Objetivo: Exibir todos os itens da tabela estoque no banco de dados exerciciodois
	 * Parametros: Nao ha parametros
	 * */
	public static void listar() {
		//Declarando variavel de acesso a tabela e conectando a tabela
		DAO dao = new DAO();
		dao.conectar();
		
		//Variaveis
		Estoque[] listadeprodutos = null;
		
		//Armazenar todos os itens da tabela no array
		listadeprodutos = dao.getestoque();
		
		//Exibindo cada item do array
		for(int i = 0; i < listadeprodutos.length; i = i + 1) {
			System.out.println(listadeprodutos[i].toString());
		}
		
		//Desconectando da tabela
		dao.close();
	}
	
	/*
	 * Indentificacao: inserir()
	 * Objetivo: Criar e inserir itens na tabela estoque no banco de dados exerciciodois apartir dos dados lidos da entrada padrao
	 * Parametros: Nao ha parametros
	 * */
	public static void inserir() {
		//Declarando variavel de acesso a tabela e conectando a tabela
		DAO dao = new DAO();
		dao.conectar();
		
		//Variaveis
		Estoque produtoinserir = null;
		int codigo, quantidade;
		double valor;
		String produto;
		boolean resp;
		
		//Pedir e ler os dados do novo item e armazenar nas respectivas variaveis
		System.out.println("Codigo: ");
		codigo = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Produto: ");
		produto = sc.nextLine();
		
		System.out.println("Valor: ");
		valor = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Quantidade: ");
		quantidade = sc.nextInt();
		sc.nextLine();
		
		//Unir os dados para criar o novo item
		produtoinserir = new Estoque(codigo, produto, valor, quantidade);
		resp = dao.inserirproduto(produtoinserir);
		
		//Verificar se a insercao foi bem sucedida
		if(resp)
		{
			System.out.println("Produto inserido com sucesso");
		} else {
			System.out.println("Falha ao inserir o produto");
		}
		
		//Desconectando da tabela
		dao.close();
	}
	/*
	 * Indentificacao: excluir()
	 * Objetivo: Excluir item da tabela estoque no banco de dados exerciciodois apartir do codigo inserido.
	 * Parametros: Nao ha parametros
	 * */
	public static void excluir() {
		//Declarando variavel de acesso a tabela e conectando a tabela
		DAO dao = new DAO();
		dao.conectar();
		
		//Variaveis
		boolean resp;
		int codigo;
		
		//Pedir e ler o codigo do item para ser excluido 
		System.out.println("Codigo:");
		codigo = sc.nextInt();
		sc.nextLine();
		
		//Realizar a exclusao do item
		resp = dao.excluirproduto(codigo);
		
		//Verificar se a exclusao foi bem sucedida
		if(resp) {
			System.out.println("Produto excluido com sucesso");
		} else {
			System.out.println("Falha ao excluir o produto");
		}
		
		//Desconectando da tabela
		dao.close();
	}
	
	/*
	 * Indentificacao: atualizar()
	 * Objetivo: Atualizar item da tabela estoque no banco de dados exerciciodois apartir do codigo inserido.
	 * Parametros: Nao ha parametros
	 * */
	public static void atualizar() {
		//Declarando variavel de acesso a tabela e conectando a tabela
		DAO dao = new DAO();
		dao.conectar();
		
		//Variaveis
		Estoque produtoatualizar= null;
		int codigo, quantidade;
		double valor;
		String produto;
		boolean resp;
		
		//Pedir e ler os dados a serem atualizados no item e armazenar nas respectivas variaveis
		System.out.println("Codigo: ");
		codigo = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Produto: ");
		produto = sc.nextLine();
		
		System.out.println("Valor: ");
		valor = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Quantidade: ");
		quantidade = sc.nextInt();
		sc.nextLine();
		
		//Unir os dados para atualizar o item
		produtoatualizar= new Estoque(codigo, produto, valor, quantidade);
		resp = dao.atualizarproduto(produtoatualizar);
		
		//Verificar se a atualizacao foi bem sucedida
		if(resp)
		{
			System.out.println("Produto atualizado com sucesso");
		} else {
			System.out.println("Falha ao atualizar o produto");
		}
		
		//Desconectando da tabela
		dao.close();
	}
	
	/*
	 * Indentificacao: excluir()
	 * Objetivo: Menu de escolha de opcao do CRUD do DAO
	 * Parametros: Nao ha parametros
	 * */
	public static void main(String args[]) {
		//Variaveis
		int opcao = 0;
		
		do {
			//Exibir lista de opcoes e ler a opcao inserida
			System.out.println("1 - Listar");
			System.out.println("2 - Inserir");
			System.out.println("3 - Excluir");
			System.out.println("4 - Atualizar");
			System.out.println("5 - Sair");
			opcao = sc.nextInt();
			
			//Executar uma opcao de acordo com a escolha
			switch(opcao) {
			case 1: listar(); break;
			case 2: inserir(); break;
			case 3: excluir(); break;
			case 4: atualizar(); break;
			case 5: System.out.println("Acesso ao banco de dados encerrado"); break;
			default: System.out.println("Opcao invalida!");
			}
		
		}
		while(opcao != 5);
	}
}
