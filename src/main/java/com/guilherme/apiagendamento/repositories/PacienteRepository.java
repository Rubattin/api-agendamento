package com.guilherme.apiagendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.guilherme.apiagendamento.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
