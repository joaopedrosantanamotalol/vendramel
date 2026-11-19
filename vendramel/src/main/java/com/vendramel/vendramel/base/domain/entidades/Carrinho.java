package com.vendramel.vendramel.base.domain.entidades;

import java.time.LocalDateTime;
import java.util.List;

public class Carrinho {
    
    private int id;
    private LocalDateTime dataCriacao;
    private List<ItemCarrinho> itens;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public List<ItemCarrinho> getItens() {
        return itens;
    }
    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }
    

}
