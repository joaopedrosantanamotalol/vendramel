package com.vendramel.vendramel.base.infra.mapper.ItemCarrinho;


public class ItemCarrinhoMapper {

    public static com.vendramel.vendramel.base.domain.entidades.ItemCarrinho paraDominio(
            com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemCarrinho item) {

        if (item == null) {
            return null;
        }

        var dominio = new com.vendramel.vendramel.base.domain.entidades.ItemCarrinho();

        dominio.setId(item.getId());
        dominio.setQuantidade(item.getQuantidade());

        return dominio;
    }
}

