package com.guilherme.apiagendamento.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private String data; // Formato: "10/04/2025"

    @Column(nullable = false)
    private String hora; // Formato: "15:00"

    @Column(nullable = false)
    private String status; // agendada, cancelada, realizada
}

