package com.vendramel.vendramel.base.infra.repository.Pedido;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Pedido;

public interface PedidoJpaRepository extends JpaRepository<Pedido, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Pedido p WHERE p.id = :id")
    Optional<Pedido> buscarPorIdComBloqueio(@Param("id") int id);
}