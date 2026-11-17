package com.vendramel.vendramel.base.domain.interfaces;

import java.util.List;

import com.vendramel.vendramel.base.domain.entidades.Endereco;
import com.vendramel.vendramel.base.domain.entidades.ItemCarrinho;

public interface FreteInterface {
    void calculcarFrete(Endereco endereco, List<ItemCarrinho> itens);
}
