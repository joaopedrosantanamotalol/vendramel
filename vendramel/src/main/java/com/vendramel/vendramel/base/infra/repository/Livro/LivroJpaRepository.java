package com.vendramel.vendramel.base.infra.repository.Livro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Livro;

import jakarta.persistence.LockModeType;

public interface LivroJpaRepository extends JpaRepository<Livro, Integer> {

@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT l FROM Livro l WHERE l.id = :id")
Optional<Livro> buscarPorIdComBloqueio(@Param("id") int id);

}
