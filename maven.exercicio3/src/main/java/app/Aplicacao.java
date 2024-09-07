package app;

import static spark.Spark.*;
import service.ProdutoService;
public class Aplicacao {
	private static ProdutoService produtoservice = new ProdutoService();
	
	public static void main(String[] args) {
		port(6789);
		
		post("/produto", (request, response) -> produtoservice.add(request, response));
		
		get("/produto/:id", (request, response) -> produtoservice.get(request, response));
		
		get("/produto/update/:id", (request, response) -> produtoservice.update(request, response));
		
		get("/produto/delete/:id", (request, response) -> produtoservice.delete(request, response));
		
		get("/produto", (request, response) -> produtoservice.getAll(request, response));
	}
}
