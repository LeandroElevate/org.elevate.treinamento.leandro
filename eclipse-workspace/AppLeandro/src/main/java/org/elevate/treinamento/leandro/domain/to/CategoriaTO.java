package org.elevate.treinamento.leandro.domain.to;

public class CategoriaTO {

	private Long id;
	private String descricao;

	public CategoriaTO() {
		super();
	}

	public CategoriaTO(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}