package com.vendramel.vendramel.base.infra.repository.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Pedido;

public interface PedidoJpaRepository extends JpaRepository<Pedido, Integer> {
}