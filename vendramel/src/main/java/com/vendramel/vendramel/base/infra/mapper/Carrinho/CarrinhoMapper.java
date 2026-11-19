package com.vendramel.vendramel.base.infra.mapper.Carrinho;


import java.util.Collections;
import java.util.stream.Collectors;

import com.vendramel.vendramel.base.infra.mapper.ItemCarrinho.ItemCarrinhoMapper;

public class CarrinhoMapper {

    public static com.vendramel.vendramel.base.domain.entidades.Carrinho paraDominio(
            com.vendramel.vendramel.base.infra.entidadesFrameWork.Carrinho carrinho) {

        if (carrinho == null) {
            return null;
        }

        var dominio = new com.vendramel.vendramel.base.domain.entidades.Carrinho();

        dominio.setId(carrinho.getId());
        dominio.setDataCriacao(carrinho.getDataCriacao());

        dominio.setItens(
            carrinho.getItens() == null
                ? Collections.emptyList()
                : carrinho.getItens().stream()
                    .map(ItemCarrinhoMapper::paraDominio)
                    .collect(Collectors.toList())
        );

        return dominio;
    }
}
