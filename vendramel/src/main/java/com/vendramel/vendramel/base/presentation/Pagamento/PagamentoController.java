
package com.vendramel.vendramel.base.presentation.Pagamento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vendramel.vendramel.base.application.usecase.pagamento.CriarPagamentoUseCase;
import com.vendramel.vendramel.base.domain.entidades.Pagamento;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final CriarPagamentoUseCase criarPagamentoUseCase;

    public PagamentoController(
            CriarPagamentoUseCase criarPagamentoUseCase) {
        this.criarPagamentoUseCase = criarPagamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(
            @RequestBody CriarPagamentoRequest request) {

        Pagamento pagamento = criarPagamentoUseCase.executar(
                request.pedidoId(),
                request.tipo(),
                request.parcelas()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    public record CriarPagamentoRequest(
            int pedidoId,
            String tipo,
            int parcelas
    ) {}
}
