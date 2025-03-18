package com.guilherme.apiagendamento.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.guilherme.apiagendamento.models.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
