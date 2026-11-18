package com.vendramel.vendramel.base.domain.interfaces;

import java.util.List;

import com.vendramel.vendramel.base.domain.entidades.ItemPedido;

public interface ItemPedidoInterface {

    ItemPedido criar(ItemPedido pedido);
    ItemPedido editar(ItemPedido pedido);
    List<ItemPedido> buscarTodos();
    void deletarPorId(int id);
    ItemPedido procurarPorId(int id);
    
}
