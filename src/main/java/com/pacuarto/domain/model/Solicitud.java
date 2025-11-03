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

    @OneToOne(mappedBy = "solicitud", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Entrega entrega;

    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Receptor receptor;

    @ManyToOne
    @JoinColumn(name = "donacion_id")
    private Donacion donacion;
}
