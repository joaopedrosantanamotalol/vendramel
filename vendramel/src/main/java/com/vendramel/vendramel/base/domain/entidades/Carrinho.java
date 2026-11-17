package com.vendramel.vendramel.base.domain.entidades;

import java.time.LocalDateTime;

public class Carrinho {
    
    private int id;
    private LocalDateTime dataCriacao;
    private ItemCarrinho item;


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
    public ItemCarrinho getItem() {
        return item;
    }
    public void setItem(ItemCarrinho item) {
        this.item = item;
    }


}
