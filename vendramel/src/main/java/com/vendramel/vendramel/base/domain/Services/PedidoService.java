package com.vendramel.vendramel.base.domain.Services;

import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.PedidoInterface;

public class PedidoService {

    private final PedidoInterface inter;

    public PedidoService(PedidoInterface inter) {
        this.inter = inter;
    }

    public Pedido efetuarPedido(int idCliente, int idEndereco){
        return inter.efetuarPedido(idCliente, idEndereco);
    }

    public Pedido finalizarPedido(int idPedido){
        return inter.finalizarPedido(idPedido);
    }
    public Pedido cancelarPedido(int pedido){
        return inter.cancelarPedido(pedido);
    }

    public Pedido buscarPorId(int id){
        return inter.buscarPorId(id);
    }

}
