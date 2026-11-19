package com.vendramel.vendramel.base.infra.repository.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Cliente;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> {
}