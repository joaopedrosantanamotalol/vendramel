package com.vendramel.vendramel.base.domain.Services;

import com.vendramel.vendramel.base.domain.entidades.Carrinho;
import com.vendramel.vendramel.base.domain.interfaces.CarrinhoInterface;

public class CarrinhoService {

    private final CarrinhoInterface inter;

    public CarrinhoService(CarrinhoInterface inter) {
        this.inter = inter;
    }

    public Carrinho vizualizarItem(int idCliente){
        return inter.visualizarCarrinho(idCliente);
    }
    
    public Carrinho visualizarCarrinho(int idCliente){
        return inter.visualizarCarrinho(idCliente);
    }

    public Carrinho alterarQuantidade(int idItem, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                "A quantidade deve ser maior que zero."
            );
        }

        return inter.alterarQuantidade(idItem, quantidade);
    }

    public void removerItem(int idCarrinho){
        inter.removerItem(idCarrinho);
    }
    
    public void destruirItens(int idCarrinho){
        inter.destruirItens(idCarrinho);
    }



}
