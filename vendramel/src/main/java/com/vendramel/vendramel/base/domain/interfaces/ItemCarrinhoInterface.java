package com.vendramel.vendramel.base.domain.interfaces;

import com.vendramel.vendramel.base.domain.entidades.Carrinho;

public interface ItemCarrinhoInterface {

    Carrinho adicionarItem(int idCliente, int idLivro, int quantidade);

}