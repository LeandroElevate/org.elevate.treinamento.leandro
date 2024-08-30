package org.elevate.treinamento.leandro.repository;

import org.elevate.treinamento.leandro.domain.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroEntity, String>{

}