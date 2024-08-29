package org.elevate.treinamento.diogo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_CATEGORIAS")
public class CategoriaEntity {
	
	@Id
	private Long id;
	private String descricao;
	
	
	public CategoriaEntity() {
		super();
	}
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
