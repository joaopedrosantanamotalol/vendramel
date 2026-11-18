package com.vendramel.vendramel.base.domain.Services;

import java.util.List;

import com.vendramel.vendramel.base.domain.entidades.ItemPedido;
import com.vendramel.vendramel.base.domain.interfaces.ItemPedidoInterface;

public class ItemPedidoService {
    
    private final ItemPedidoInterface inter;

    public ItemPedidoService(ItemPedidoInterface inter) {
        this.inter = inter;
    }

    public ItemPedido criar(ItemPedido pedido){
        return inter.criar(pedido);
    }
    
    public ItemPedido editar(ItemPedido pedido){
        return inter.editar(pedido);
    }
    
    public List<ItemPedido> buscarTodos(){
        return inter.buscarTodos();
    }
    
    public void deletarPorId(int id){
        inter.deletarPorId(id);
    }
    
    public ItemPedido procurarPorId(int id){
        return inter.procurarPorId(id);
    }
    

}
