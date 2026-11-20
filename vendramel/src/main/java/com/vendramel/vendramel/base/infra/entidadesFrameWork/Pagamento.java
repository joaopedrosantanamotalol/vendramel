package com.vendramel.vendramel.base.infra.entidadesFrameWork;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Pagamento")
public class Pagamento {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String formaPagamento;

    @Column(nullable = false)
    private int parcelas;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime dataPagamento;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public int getParcelas() {
        return parcelas;
    }
    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    
    
}
