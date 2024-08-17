package com.ti2cc;

public class Estoque {
	private int codigo;
	private String produto;
	private double valor;
	private int quantidade;
	
	public Estoque() {
		this.codigo = -1;
		this.produto = "";
		this.valor = 0.0;
		this.quantidade = 0;
	}
	
	public Estoque(int codigo, String produto, double valor, int quantidade) {
		this.codigo = codigo;
		this.produto = produto;
		this.valor = valor;
		this.quantidade = quantidade;		
	}
	
	public int getcodigo() {
		return codigo;
	}
	
	public void setcodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getproduto() {
		return produto;
	}
	
	public void setproduto(String produto) {
		this.produto = produto;
	}
	
	public double getvalor() {
		return valor;
	}
	
	public void setvalor(double valor) {
		this.valor = valor;
	}
	
	public int getquantidade() {
		return quantidade;
	}
	
	public void setquantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
		return "Estoque [codigo=" + codigo + ", produto=" + produto + ", valor=" + valor + ", quantidade=" + quantidade + "]";
	}
}
