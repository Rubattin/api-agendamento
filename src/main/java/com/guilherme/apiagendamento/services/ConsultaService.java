package com.guilherme.apiagendamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guilherme.apiagendamento.repositories.ConsultaRepository;
import com.guilherme.apiagendamento.models.Consulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta salvar(Consulta consulta) {
        validarAgendamento(consulta);
        return consultaRepository.save(consulta);
    }

    public void deletar(Long id) {
        consultaRepository.deleteById(id);
    }

    private void validarAgendamento(Consulta consulta) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
    
        LocalDate data = LocalDate.parse(consulta.getData(), formatoData);
        LocalTime hora = LocalTime.parse(consulta.getHora(), formatoHora);
    
        // 🔹 Regra 1: A data não pode estar no passado
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Não é possível agendar consultas para datas passadas.");
        }
    
        // 🔹 Regra 2: O horário deve ser entre 08:00 e 18:00
        if (hora.isBefore(LocalTime.of(8, 0)) || hora.isAfter(LocalTime.of(18, 0))) {
            throw new IllegalArgumentException("O horário da consulta deve estar entre 08:00 e 18:00.");
        }
    
        // 🔹 Regra 3: O médico não pode ter outra consulta no mesmo horário
        List<Consulta> consultasMedico = consultaRepository.findAll();
        for (Consulta c : consultasMedico) {
            if (c.getMedico().getId().equals(consulta.getMedico().getId()) &&
                c.getData().equals(consulta.getData()) && c.getHora().equals(consulta.getHora())) {
                throw new IllegalArgumentException("O médico já possui uma consulta nesse horário.");
            }
        }
    
        // 🔹 Regra 4: O paciente não pode ter outra consulta no mesmo horário
        for (Consulta c : consultasMedico) {
            if (c.getPaciente().getId().equals(consulta.getPaciente().getId()) &&
                c.getData().equals(consulta.getData()) && c.getHora().equals(consulta.getHora())) {
                throw new IllegalArgumentException("O paciente já possui uma consulta nesse horário.");
            }
        }
    }

}    