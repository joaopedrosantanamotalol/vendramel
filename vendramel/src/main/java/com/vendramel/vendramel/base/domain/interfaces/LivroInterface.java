package com.vendramel.vendramel.base.domain.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.vendramel.vendramel.base.domain.entidades.ItemPedido;
import com.vendramel.vendramel.base.domain.entidades.Livro;

public interface LivroInterface {

    BigDecimal calcularPreco(Livro livro);

    void atualizarEstoque(List<ItemPedido> itens);

    Livro buscarPorid(int id);

    Livro cadastrar(Livro livro);
}