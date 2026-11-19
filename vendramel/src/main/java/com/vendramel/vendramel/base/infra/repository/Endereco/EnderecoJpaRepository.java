package com.vendramel.vendramel.base.infra.repository.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.Endereco;

public interface EnderecoJpaRepository
        extends JpaRepository<Endereco, Integer> {
}