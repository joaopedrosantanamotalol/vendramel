package com.vendramel.vendramel.base.domain.Services;

import com.vendramel.vendramel.base.domain.entidades.Endereco;
import com.vendramel.vendramel.base.domain.interfaces.EnderecoInterface;

public class EnderecoService {

    private final EnderecoInterface inter;

    public EnderecoService(EnderecoInterface inter) {
        this.inter = inter;
    }

    public Endereco buscarPorId(int id) {
        return inter.buscarPorId(id);
    }
    public Endereco cadastrar(Endereco endereco) {
    return inter.cadastrar(endereco);
}
}