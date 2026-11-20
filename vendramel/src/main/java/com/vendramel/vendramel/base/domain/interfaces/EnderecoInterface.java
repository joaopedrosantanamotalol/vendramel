package com.vendramel.vendramel.base.domain.interfaces;

import com.vendramel.vendramel.base.domain.entidades.Endereco;

public interface EnderecoInterface {
    Endereco buscarPorId(int id);
    Endereco cadastrar(Endereco endereco);
}
