package com.vendramel.vendramel.base.application.usecase.pedido;

import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.PedidoInterface;

public class CancelarPedidoUseCase {

    private final PedidoInterface inter;

    public CancelarPedidoUseCase(PedidoInterface inter) {
        this.inter = inter;
    }

    public Pedido executar(int idPedido){
        
        Pedido ped = inter.buscarPorId(idPedido);

        if(ped == null){
            throw new RuntimeException("não há pedido com esse id");
        }

        ped.setStatus("cancelado");

        return inter.cancelarPedido(idPedido);
    }

}
