package com.vendramel.vendramel.base.application.dto.frete;

import java.util.List;

import com.vendramel.vendramel.base.domain.entidades.Endereco;
import com.vendramel.vendramel.base.domain.entidades.ItemCarrinho;

public record FreteRequest(
    Endereco endereco,
    List<ItemCarrinho> itens
) {}
