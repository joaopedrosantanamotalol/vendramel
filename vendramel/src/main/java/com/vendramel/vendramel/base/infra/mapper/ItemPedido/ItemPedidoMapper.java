
package com.vendramel.vendramel.base.infra.mapper.ItemPedido;

import com.vendramel.vendramel.base.infra.mapper.Livro.LivroMapper;

public class ItemPedidoMapper {

    public static com.vendramel.vendramel.base.domain.entidades.ItemPedido
            paraDominio(
            com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemPedido item) {

        if (item == null) {
            return null;
        }

        var dominio =
                new com.vendramel.vendramel.base.domain.entidades.ItemPedido();

        dominio.setId(item.getId());
        dominio.setQuantidade(item.getQuantidade());
        dominio.setPrecoUnitario(item.getPrecoUnitario());
        dominio.setLivro(LivroMapper.paraDominio(item.getLivro()));

        return dominio;
    }

    public static com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemPedido
            paraPersistencia(
            com.vendramel.vendramel.base.domain.entidades.ItemPedido item) {

        if (item == null) {
            return null;
        }

        var persistencia =
                new com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemPedido();

        persistencia.setId(item.getId());
        persistencia.setQuantidade(item.getQuantidade());
        persistencia.setPrecoUnitario(item.getPrecoUnitario());
        persistencia.setLivro(
                LivroMapper.paraPersistencia(item.getLivro())
        );

        return persistencia;
    }
}
