package com.vendramel.vendramel.base.application.usecase.pedido;

import com.vendramel.vendramel.base.domain.entidades.Cliente;
import com.vendramel.vendramel.base.domain.entidades.Endereco;
import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.ClienteInterface;
import com.vendramel.vendramel.base.domain.interfaces.EnderecoInterface;
import com.vendramel.vendramel.base.domain.interfaces.PedidoInterface;

public class CriarPedidoUseCase {
    
    private final PedidoInterface pedidoInter;
    private final ClienteInterface inter;
    private final EnderecoInterface enderecointer;


    public CriarPedidoUseCase(PedidoInterface pedidoInter,ClienteInterface inter,EnderecoInterface enderecointer) {
        this.pedidoInter = pedidoInter;
        this.inter = inter;
        this.enderecointer = enderecointer;
    }

    public Pedido efetuarPedido(int id, int enderecoId){

        Cliente cliente = inter.buscarPorId(id);
        Endereco endereco = enderecointer.buscarPorId(enderecoId);


        if(cliente == null){
            throw new RuntimeException("não existe cliente com esse id");
        }

        if(endereco == null){
            throw new RuntimeException("não existe endereco com esse id");
        }

        int idCliente = cliente.getId();
        
        int idEndereco = endereco.getId();

        return pedidoInter.efetuarPedido(idCliente,idEndereco);
    }

}
