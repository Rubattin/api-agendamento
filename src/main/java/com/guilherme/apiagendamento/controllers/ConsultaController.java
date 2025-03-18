package com.guilherme.apiagendamento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.guilherme.apiagendamento.services.ConsultaService;
import com.guilherme.apiagendamento.models.Consulta;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> listarTodas() {
        return consultaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<Consulta> buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> agendarConsulta(@RequestBody Consulta consulta) {
        try {
            Consulta novaConsulta = consultaService.salvar(consulta);
            return ResponseEntity.ok(novaConsulta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarConsulta(@PathVariable Long id) {
        if (consultaService.buscarPorId(id).isPresent()) {
            consultaService.deletar(id);
            return ResponseEntity.ok("Consulta cancelada com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Consulta não encontrada.");
        }
    }
}
