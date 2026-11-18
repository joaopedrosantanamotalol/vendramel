package com.vendramel.vendramel.base.application.usecase.cliente;

import com.vendramel.vendramel.base.domain.Services.ClienteService;
import com.vendramel.vendramel.base.domain.entidades.Cliente;

public class CriarUsuarioUseCase {
    
    private final ClienteService service;

    public CriarUsuarioUseCase(ClienteService service) {
        this.service = service;
    }

    public Cliente executar(Cliente corpo){
        return service.cadastrar(corpo);
    }

}
