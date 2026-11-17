package com.vendramel.vendramel.base.domain.Services;

import java.util.List;

import com.vendramel.vendramel.base.domain.entidades.Endereco;
import com.vendramel.vendramel.base.domain.entidades.ItemCarrinho;
import com.vendramel.vendramel.base.domain.interfaces.FreteInterface;

public class FreteService {

    private final FreteInterface inter;

    public FreteService(FreteInterface inter) {
        this.inter = inter;
    }


   public void calculcarFrete(Endereco endereco, List<ItemCarrinho> itens){
        inter.calculcarFrete(endereco, itens);
   }

}
