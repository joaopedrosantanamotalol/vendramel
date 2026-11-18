package com.vendramel.vendramel.base.application.usecase.pedido;

import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.PedidoInterface;

public class BuscarPorIdUseCase {
    
    private final PedidoInterface inter;

    public BuscarPorIdUseCase(PedidoInterface inter) {
        this.inter = inter;
    }

    public Pedido executar(int id){

        Pedido ped = inter.buscarPorId(id);

        if(ped == null){
            throw new RuntimeException("não há pedido com esse id");
        }  

        return inter.buscarPorId(id);
    }

}
