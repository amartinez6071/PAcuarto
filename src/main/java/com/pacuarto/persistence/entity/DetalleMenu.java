package com.pacuarto.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMenu{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;
}
