package com.pacuarto.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleDonacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    // Muchos a Uno con Menu
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    // Muchos a Uno con Donacion
    @ManyToOne
    @JoinColumn(name = "donacion_id", nullable = false)
    private Donacion donacion;
}
