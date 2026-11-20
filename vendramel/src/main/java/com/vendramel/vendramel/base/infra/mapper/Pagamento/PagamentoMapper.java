package com.vendramel.vendramel.base.infra.mapper.Pagamento;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.Pedido;

public class PagamentoMapper {

    public static Pagamento paraDominio(
            com.vendramel.vendramel.base.infra.entidadesFrameWork.Pagamento pagamento) {

        if (pagamento == null) {
            return null;
        }

        Pagamento dominio = new Pagamento();

        dominio.setId(pagamento.getId());
        dominio.setFormaPagamento(pagamento.getFormaPagamento());
        dominio.setParcelas(pagamento.getParcelas());
        dominio.setValor(pagamento.getValor());
        dominio.setStatus(pagamento.getStatus());
        dominio.setDataPagamento(pagamento.getDataPagamento());

        return dominio;
    }

    public static com.vendramel.vendramel.base.infra.entidadesFrameWork.Pagamento paraPersistencia(
            Pagamento pagamento, Pedido pedido) {

        if (pagamento == null) {
            return null;
        }

        com.vendramel.vendramel.base.infra.entidadesFrameWork.Pagamento persistencia =
                new com.vendramel.vendramel.base.infra.entidadesFrameWork.Pagamento();

        persistencia.setId(pagamento.getId());
        persistencia.setFormaPagamento(pagamento.getFormaPagamento());
        persistencia.setParcelas(pagamento.getParcelas());
        persistencia.setValor(pagamento.getValor());
        persistencia.setStatus(pagamento.getStatus());
        persistencia.setDataPagamento(pagamento.getDataPagamento());
        persistencia.setPedido(pedido);

        return persistencia;
    }
}