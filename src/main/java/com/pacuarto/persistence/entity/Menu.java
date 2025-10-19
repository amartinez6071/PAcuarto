package com.pacuarto.persistence.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String detalle;

    @ManyToOne
    @JoinColumn(name="donante_id")
    private Donante donante;

    @OneToMany(mappedBy="menu", fetch=FetchType.LAZY)
    private List<DetalleMenu> detalleProductos;

    @OneToMany(mappedBy="menu",fetch=FetchType.LAZY)
    private List<DetalleDonacion>detalleDonaciones;


}