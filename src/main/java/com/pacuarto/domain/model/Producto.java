package com.pacuarto.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    private String cantidad;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetalleMenu> detalleMenus;
}
