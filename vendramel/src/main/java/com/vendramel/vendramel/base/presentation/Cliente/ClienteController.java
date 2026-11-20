
package com.vendramel.vendramel.base.presentation.Cliente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vendramel.vendramel.base.domain.Services.ClienteService;
import com.vendramel.vendramel.base.domain.entidades.Cliente;
import com.vendramel.vendramel.base.domain.entidades.Endereco;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteResponse> autenticar(
            @RequestBody LoginRequest request) {

        Cliente cliente = clienteService.autenticar(
                request.email(),
                request.senha()
        );

        return ResponseEntity.ok(ClienteResponse.from(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(
            @RequestBody Cliente cliente) {

        Cliente cadastrado = clienteService.cadastrar(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ClienteResponse.from(cadastrado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(
            @PathVariable int id,
            @RequestBody Cliente cliente) {

        Cliente atualizado = clienteService.atualizar(id, cliente);

        return ResponseEntity.ok(ClienteResponse.from(atualizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(
            @PathVariable int id) {

        Cliente cliente = clienteService.buscarPorId(id);

        return ResponseEntity.ok(ClienteResponse.from(cliente));
    }

    public record LoginRequest(
            String email,
            String senha
    ) {}

    public record ClienteResponse(
            int id,
            String nomeCompleto,
            String cpf,
            LocalDateTime dataNascimento,
            String email,
            String telefone,
            Endereco endereco
    ) {
        public static ClienteResponse from(Cliente cliente) {
            return new ClienteResponse(
                    cliente.getId(),
                    cliente.getNomeCompleto(),
                    cliente.getCpf(),
                    cliente.getDataNascimento(),
                    cliente.getEmail(),
                    cliente.getTelefone(),
                    cliente.getEndereco()
            );
        }
    }
}
