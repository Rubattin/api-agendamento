package com.guilherme.apiagendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.guilherme.apiagendamento.models.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}

