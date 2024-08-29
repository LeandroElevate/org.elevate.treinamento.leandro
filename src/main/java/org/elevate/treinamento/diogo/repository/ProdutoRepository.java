package org.elevate.treinamento.diogo.repository;


import org.elevate.treinamento.diogo.domain.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
}