package com.vendramel.vendramel.base.infra.repository.ItemPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemPedido;

public interface ItemPedidoJpaRepository extends JpaRepository<ItemPedido, Integer> {

    List<ItemPedido> findAllByPedido_Id(int pedidoId);

}
