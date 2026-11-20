package com.vendramel.vendramel.base.domain.Services;

import com.vendramel.vendramel.base.domain.entidades.Carrinho;
import com.vendramel.vendramel.base.domain.interfaces.ItemCarrinhoInterface;

public class ItemCarrinhoService {

    private final ItemCarrinhoInterface inter;

    public ItemCarrinhoService(ItemCarrinhoInterface inter) {
        this.inter = inter;
    }

    public Carrinho adicionarItem(int idCliente, int idLivro, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        return inter.adicionarItem(idCliente, idLivro, quantidade);
    }
}