package com.vendramel.vendramel.base.domain.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {

    private int id;
    private LocalDateTime dataPedido;
    private BigDecimal subtotal;
    private BigDecimal valorFrete;
    private BigDecimal desconto;
    private BigDecimal valorTotal;
    private String status;
    private ItemPedido itemPedido;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    public BigDecimal getValorFrete() {
        return valorFrete;
    }
    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }
    public BigDecimal getDesconto() {
        return desconto;
    }
    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ItemPedido getItemPedido() {
        return itemPedido;
    }
    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    

}
