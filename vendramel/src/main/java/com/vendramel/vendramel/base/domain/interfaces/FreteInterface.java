
package com.vendramel.vendramel.base.domain.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.vendramel.vendramel.base.domain.entidades.Endereco;
import com.vendramel.vendramel.base.domain.entidades.ItemCarrinho;

public interface FreteInterface {

    BigDecimal calcularFrete(
        Endereco endereco,
        List<ItemCarrinho> itens
    );
}
