
package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Frete;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vendramel.vendramel.base.domain.entidades.Endereco;
import com.vendramel.vendramel.base.domain.entidades.ItemCarrinho;
import com.vendramel.vendramel.base.domain.interfaces.FreteInterface;

@Repository
public class FreteImp implements FreteInterface {


private static final Map<String, BigDecimal> FRETES = Map.ofEntries(
    Map.entry("SAO PAULO", new BigDecimal("15.00")),
    Map.entry("GUARULHOS", new BigDecimal("18.00")),
    Map.entry("OSASCO", new BigDecimal("18.00")),
    Map.entry("SANTO ANDRE", new BigDecimal("20.00")),
    Map.entry("SAO BERNARDO DO CAMPO", new BigDecimal("22.00")),
    Map.entry("SAO CAETANO DO SUL", new BigDecimal("20.00")),
    Map.entry("DIADEMA", new BigDecimal("22.00")),
    Map.entry("MOGI DAS CRUZES", new BigDecimal("25.00")),
    Map.entry("CAMPINAS", new BigDecimal("30.00")),
    Map.entry("SANTOS", new BigDecimal("35.00")),
    Map.entry("JUNDIAI", new BigDecimal("30.00")),
    Map.entry("SOROCABA", new BigDecimal("35.00")),
    Map.entry("RIBEIRAO PRETO", new BigDecimal("50.00")),
    Map.entry("SAO JOSE DOS CAMPOS", new BigDecimal("40.00"))
);


    @Override
    public BigDecimal calcularFrete(
            Endereco endereco,
            List<ItemCarrinho> itens) {

        if (itens == null || itens.isEmpty()) {
            throw new IllegalStateException(
                "Não é possível calcular o frete com o carrinho vazio."
            );
        }

        if (endereco == null || endereco.getCidade() == null) {
            throw new IllegalArgumentException(
                "Endereço de entrega inválido."
            );
        }

        String cidade = endereco.getCidade()
                .trim()
                .toUpperCase()
                .replace("Á", "A")
                .replace("Ã", "A")
                .replace("Â", "A")
                .replace("É", "E")
                .replace("Ê", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ô", "O")
                .replace("Õ", "O")
                .replace("Ú", "U")
                .replace("Ç", "C");

        BigDecimal frete = FRETES.get(cidade);

        if (frete == null) {
            return new BigDecimal("25.00");
        }

        return frete;
    }
}
