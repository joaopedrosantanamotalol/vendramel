package com.vendramel.vendramel.base.infra.repository.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Livro;

public interface LivroJpaRepository extends JpaRepository<Livro, Integer> {
}
