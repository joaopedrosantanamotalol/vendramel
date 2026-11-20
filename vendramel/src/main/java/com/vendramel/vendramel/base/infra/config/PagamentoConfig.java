
package com.vendramel.vendramel.base.infra.config;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vendramel.vendramel.base.application.usecase.pagamento.CalcularDescontoUseCase;
import com.vendramel.vendramel.base.application.usecase.pagamento.CriarPagamentoUseCase;
import com.vendramel.vendramel.base.domain.Services.PagamentoService;
import com.vendramel.vendramel.base.domain.Services.CalcularDescontoJuros;
import com.vendramel.vendramel.base.domain.Services.PedidoService;
import com.vendramel.vendramel.base.domain.Services.LivroService;
import com.vendramel.vendramel.base.domain.Services.CarrinhoService;
import com.vendramel.vendramel.base.domain.Services.ClienteService;
import com.vendramel.vendramel.base.domain.Services.EnderecoService;
import com.vendramel.vendramel.base.domain.Services.FreteService;
import com.vendramel.vendramel.base.domain.interfaces.AplicarDescontoInterface;
import com.vendramel.vendramel.base.domain.interfaces.PagamentoInterface;
import com.vendramel.vendramel.base.domain.interfaces.PedidoInterface;
import com.vendramel.vendramel.base.domain.interfaces.LivroInterface;
import com.vendramel.vendramel.base.domain.interfaces.CarrinhoInterface;
import com.vendramel.vendramel.base.domain.interfaces.ClienteInterface;
import com.vendramel.vendramel.base.domain.interfaces.EnderecoInterface;
import com.vendramel.vendramel.base.domain.interfaces.FreteInterface;
import com.vendramel.vendramel.base.domain.interfaces.ItemCarrinhoInterface;
import com.vendramel.vendramel.base.domain.interfaces.ProcessarPagamentoInterface;

@Configuration
public class PagamentoConfig {

    @Bean
    public AplicarDescontoInterface aplicarDesconto() {
        return new CalcularDescontoJuros(new BigDecimal("0.02"));
    }

    @Bean
    public PagamentoService pagamentoService(
            Map<String, PagamentoInterface> pagamentos) {

        return new PagamentoService(pagamentos);
    }

    @Bean
    public PedidoService pedidoService(PedidoInterface inter) {
        return new PedidoService(inter);
    }

    @Bean
    public LivroService livroService(LivroInterface inter) {
        return new LivroService(inter);
    }

    @Bean
    public EnderecoService enderecoService(EnderecoInterface inter) {
        return new EnderecoService(inter);
    }

    @Bean
    public CarrinhoService carrinhoService(
            CarrinhoInterface inter,
            ItemCarrinhoInterface itemInter) {

        return new CarrinhoService(inter, itemInter);
    }

    @Bean
    public ClienteService clienteService(ClienteInterface inter) {
        return new ClienteService(inter);
    }

    @Bean
    public CalcularDescontoUseCase calcularDescontoUseCase(
            AplicarDescontoInterface aplicarDesconto) {

        return new CalcularDescontoUseCase(aplicarDesconto);
    }

    @Bean
    public CriarPagamentoUseCase criarPagamentoUseCase(
            PedidoService pedidoService,
            ProcessarPagamentoInterface processarPagamento,
            CalcularDescontoUseCase calcularDescontoUseCase) {

        return new CriarPagamentoUseCase(
                pedidoService,
                processarPagamento,
                calcularDescontoUseCase
        );
    }

    @Bean
    public FreteService freteService(FreteInterface inter) {
        return new FreteService(inter);
    }
}
