package org.elevate.treinamento.diogo.domain.to;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoTO extends BaseTO {

	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
	private String nome;

	@Positive(message = "Preço deve ser positivo")
	@DecimalMax(value = "99999.99", message = "Preço não pode ser maior que 99999.99")
	private double preco;
	
	private CategoriaTO categoria;

	public ProdutoTO() {
		super();
	}

	public CategoriaTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaTO categoria) {
		this.categoria = categoria;
	}

	public ProdutoTO(String id, String nome, double preco, CategoriaTO categoria) {
		this.setId(id);
		this.setNome(nome);
		this.setPreco(preco);
		this.setCategoria(categoria);
	}

	public ProdutoTO(String id, String nome, double preco) {
		this.setId(id);
		this.setNome(nome);
		this.setPreco(preco);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
