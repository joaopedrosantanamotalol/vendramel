package com.vendramel.vendramel.base.domain.Services;

import java.math.BigDecimal;
import java.util.List;

import com.vendramel.vendramel.base.domain.entidades.ItemPedido;
import com.vendramel.vendramel.base.domain.entidades.Livro;
import com.vendramel.vendramel.base.domain.interfaces.LivroInterface;

public class LivroService {
    
    private final LivroInterface inter;

    public LivroService(LivroInterface inter) {
        this.inter = inter;
    }

    public BigDecimal calcularPreco(Livro livro){
        return inter.calcularPreco(livro);
    }

    public void atualizarEstoque(List<ItemPedido> itens){
        inter.atualizarEstoque(itens);
    }

    public Livro buscarPorid(int id){
        return inter.buscarPorid(id);
    }


}
