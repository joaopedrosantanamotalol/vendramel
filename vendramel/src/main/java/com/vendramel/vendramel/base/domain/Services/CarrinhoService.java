
package com.vendramel.vendramel.base.domain.Services;

import com.vendramel.vendramel.base.domain.entidades.Carrinho;
import com.vendramel.vendramel.base.domain.interfaces.CarrinhoInterface;
import com.vendramel.vendramel.base.domain.interfaces.ItemCarrinhoInterface;

public class CarrinhoService {

    private final CarrinhoInterface inter;
    private final ItemCarrinhoInterface itemInter;

    public CarrinhoService(
            CarrinhoInterface inter,
            ItemCarrinhoInterface itemInter) {

        this.inter = inter;
        this.itemInter = itemInter;
    }

    public Carrinho adicionarItem(
            int idCliente,
            int idLivro,
            int quantidade) {

        return itemInter.adicionarItem(idCliente, idLivro, quantidade);
    }

    public Carrinho visualizarCarrinho(int idCliente) {
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

    public void removerItem(int idItem) {
        inter.removerItem(idItem);
    }

    public void destruirItens(int idCarrinho) {
        inter.destruirItens(idCarrinho);
    }

    public Carrinho vizualizarItem(int idCliente) {
    return inter.visualizarCarrinho(idCliente);
}

}
