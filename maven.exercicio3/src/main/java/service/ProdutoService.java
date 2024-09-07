package service;

import java.io.IOException;
import dao.DAO;
import spark.Request;
import spark.Response;
import model.Estoque;


public class ProdutoService {
	
	private DAO dao;
	
	public ProdutoService() {
		dao = new DAO();
	}
	

	public Object add(Request request, Response response) {
		Estoque produtoinserir = null;
		boolean teste;
		String resp;
		String nome = request.queryParams("nomeproduto");
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		double preco = Float.parseFloat(request.queryParams("valor"));
		dao.conectar();
		produtoinserir = new Estoque(0, nome, preco, quantidade);
		
		teste = dao.inserirproduto(produtoinserir);
		if(teste) {
			resp = "Produto inserido com sucesso";
			response.status(201);
		}else {
			resp = "Falha ao inserir o produto";
		}
		dao.close();
		
		return resp;
	}
	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Object resp = "";
		dao.conectar();
		Estoque produto = dao.getproduto(id);
		if(produto != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");
    	    
			resp = "<produto>\n" + 
	        		"\t<id>" + produto.getcodigo() + "</id>\n" +
	        		"\t<descricao>" + produto.getproduto() + "</descricao>\n" +
	        		"\t<preco>" + produto.getvalor() + "</preco>\n" +
	        		"\t<quantidade>" + produto.getquantidade() + "</quantidade>\n" +
	        		"</produto>\n";
		} else {
			response.status(404);
			resp = "Produto " + id + " nao encontrado";
		}
		dao.close();
		return resp;
	}
	
	public Object update(Request request, Response response) {
		String resp;
		int id = Integer.parseInt(request.params(":id"));
		dao.conectar();
		Estoque produto = dao.getproduto(id);
		
		if(produto != null) {
			produto.setproduto(request.queryParams("nomeproduto"));
			produto.setvalor(Float.parseFloat(request.queryParams("valor")));
			produto.setquantidade(Integer.parseInt(request.queryParams("quantidade")));
			
			dao.atualizarproduto(produto);
			resp = "" + id;
		} else {
			response.status(404);
			resp = "Produto " + id + " nao encontrado";
		}
		dao.close();
		return resp;
	}
	
	public Object delete(Request request, Response response) {
		String resp;
		boolean removido = false;
		int id = Integer.parseInt(request.params(":id"));
		dao.conectar();
		Estoque produto = dao.getproduto(id);
		
		if(produto != null) {
			removido = dao.excluirproduto(id);
			response.status(200);
			resp = "" + id;
		} else {
			response.status(404);
			resp = "Produto nao encontrado";
		}
		
		return resp;
	}
	
	public Object getAll(Request request, Response response) {
		StringBuffer resp = new StringBuffer("<produtos type=\"array\">");
		dao.conectar();
		for (Estoque produto : dao.getestoque()) {
			resp.append("\n<produto>\n" + 
            		"\t<id>" + produto.getcodigo() + "</id>\n" +
            		"\t<descricao>" + produto.getproduto() + "</descricao>\n" +
            		"\t<preco>" + produto.getvalor() + "</preco>\n" +
            		"\t<quantidade>" + produto.getquantidade()+ "</quantidade>\n" +
            		"</produto>\n");
		}
		resp.append("</produtos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return resp.toString();
	}
	
}
