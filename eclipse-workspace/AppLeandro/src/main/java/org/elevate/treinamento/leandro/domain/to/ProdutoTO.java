package org.elevate.treinamento.leandro.domain.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProdutoTO extends BaseTO{
	
	
	private String nome;
	private double preco;
	
	private CategoriaTO categoria;

	public CategoriaTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaTO categoria) {
		this.categoria = categoria;
	}
	public ProdutoTO() {
		super();
	}
	public ProdutoTO(String id, String nome, double preco, CategoriaTO categoria) {
		this.setId(id);
		this.setNome(nome);
		this.setPreco(preco);
		this.setCategoria(categoria);
	}
	@NotBlank(message = "Nome é obrigatório")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Positive(message = "Preço deve ser positivo")
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public ProdutoTO(String nome, double preco) {
		super();
		this.nome = nome;
		this.preco = preco;
	}


}
