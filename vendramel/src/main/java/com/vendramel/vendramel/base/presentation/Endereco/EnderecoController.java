
package com.vendramel.vendramel.base.presentation.Endereco;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vendramel.vendramel.base.domain.Services.EnderecoService;
import com.vendramel.vendramel.base.domain.entidades.Endereco;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(
            @RequestBody Endereco endereco) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enderecoService.cadastrar(endereco));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(
            @PathVariable int id) {

        return ResponseEntity.ok(
                enderecoService.buscarPorId(id)
        );
    }
}
