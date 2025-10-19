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
public class DetalleDonacion{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="donacion_id")
    private Donacion donacion;

    @ManyToOne
    @JoinColumn(name="manu_id")
    private Menu menu;

}
