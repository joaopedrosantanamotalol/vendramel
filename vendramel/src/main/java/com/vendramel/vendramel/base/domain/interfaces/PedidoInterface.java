package com.vendramel.vendramel.base.domain.interfaces;

import com.vendramel.vendramel.base.domain.entidades.Pedido;

public interface PedidoInterface {
    Pedido efetuarPedido(int idCliente, int idEndereco);
    Pedido finalizarPedido(int idPedido);
    Pedido cancelarPedido(int pedido);
    Pedido buscarPorId(int id);
}
