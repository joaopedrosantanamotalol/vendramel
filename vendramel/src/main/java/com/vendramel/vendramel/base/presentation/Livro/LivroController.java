package com.vendramel.vendramel.base.presentation.Livro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vendramel.vendramel.base.domain.Services.LivroService;
import com.vendramel.vendramel.base.domain.entidades.Livro;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {

        Livro cadastrado = livroService.cadastrar(livro);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable int id) {

        return ResponseEntity.ok(
                livroService.buscarPorid(id)
        );
    }
}