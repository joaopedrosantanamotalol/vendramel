
package com.vendramel.vendramel.base.infra.mapper.Pedido;

import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Pedido;
import com.vendramel.vendramel.base.infra.mapper.ItemPedido.ItemPedidoMapper;

public class PedidoMapper {

    // Persistência -> Domínio
    public static com.vendramel.vendramel.base.domain.entidades.Pedido
            paraDominio(
            Pedido pedido) {

        if (pedido == null) {
            return null;
        }

        var dominio =
                new com.vendramel.vendramel.base.domain.entidades.Pedido();

        dominio.setId(pedido.getId());
        dominio.setDataPedido(pedido.getDataPedido());
        dominio.setSubtotal(pedido.getSubtotal());
        dominio.setValorFrete(pedido.getValorFrete());
        dominio.setDesconto(pedido.getDesconto());
        dominio.setValorTotal(pedido.getValorTotal());
        dominio.setStatus(pedido.getStatus());

        dominio.setItens(
                pedido.getItens() == null
                        ? Collections.emptyList()
                        : pedido.getItens().stream()
                                .map(ItemPedidoMapper::paraDominio)
                                .collect(Collectors.toList())
        );

        return dominio;
    }

    // Domínio -> Persistência
    public static com.vendramel.vendramel.base.infra.entidadesFrameWork.Pedido
            paraPersistencia(
            com.vendramel.vendramel.base.domain.entidades.Pedido pedido) {

        if (pedido == null) {
            return null;
        }

        var persistencia =
                new com.vendramel.vendramel.base.infra.entidadesFrameWork.Pedido();

        persistencia.setId(pedido.getId());
        persistencia.setDataPedido(pedido.getDataPedido());
        persistencia.setSubtotal(pedido.getSubtotal());
        persistencia.setValorFrete(pedido.getValorFrete());
        persistencia.setDesconto(pedido.getDesconto());
        persistencia.setValorTotal(pedido.getValorTotal());
        persistencia.setStatus(pedido.getStatus());

        var itens = pedido.getItens() == null
                ? new ArrayList<com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemPedido>()
                : pedido.getItens().stream()
                        .map(ItemPedidoMapper::paraPersistencia)
                        .collect(Collectors.toList());

        persistencia.setItens(itens);
        itens.forEach(item -> item.setPedido(persistencia));
        return persistencia;
    }
}
