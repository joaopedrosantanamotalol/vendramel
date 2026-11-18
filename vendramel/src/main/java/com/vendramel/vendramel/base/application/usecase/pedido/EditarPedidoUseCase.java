package com.vendramel.vendramel.base.application.usecase.pedido;

import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.PedidoInterface;

public class EditarPedidoUseCase {

    private final PedidoInterface inter;

    public EditarPedidoUseCase(PedidoInterface inter) {
        this.inter = inter;
    }

    public Pedido finalizarPedido(int idPedido){

        Pedido pedidobuscado = inter.buscarPorId(idPedido);

        if(pedidobuscado == null){
            throw new RuntimeException("não foi possivel achar um pedido com esse id");
        }

        pedidobuscado.setStatus("finalizado");

        return inter.finalizarPedido(idPedido);
    }

}
