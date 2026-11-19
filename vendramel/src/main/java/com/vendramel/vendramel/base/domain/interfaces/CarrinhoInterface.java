package com.vendramel.vendramel.base.domain.interfaces;

import com.vendramel.vendramel.base.domain.entidades.Carrinho;

public interface CarrinhoInterface {

    Carrinho visualizarCarrinho(int idCliente);
    Carrinho alterarQuantidade(int idItem, int quantidade);
    void removerItem(int idCarrinho);
    void destruirItens(int idCarrinho);

}