package org.elevate.treinamento.leandro.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_categorias")
public class CategoriaEntity {
	
	@Id
	private Long id;
	private String descricao;
	
	
	
	public CategoriaEntity() {
		super();
	}

	public CategoriaEntity(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}