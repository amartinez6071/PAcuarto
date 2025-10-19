package com.pacuarto.persistence.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String cantidad;

    @OneToMany(mappedBy="producto",fetch=FetchType.LAZY)
    private List<DetalleMenu>detalleMenus;
}