package com.pacuarto.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(nullable = false)
    private boolean estado;

    // Relación 1:1 (lado inverso) con Entrega
    @OneToOne(mappedBy = "solicitud", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Entrega entrega;

    // Relación M:1 con Receptor
    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Receptor receptor;

    // Relación M:1 con Donacion
    @ManyToOne
    @JoinColumn(name = "donacion_id", nullable = false)
    private Donacion donacion;
}
