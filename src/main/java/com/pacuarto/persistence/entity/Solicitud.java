package com.pacuarto.persistence.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @OneToOne(mappedBy="solicitud")
    private Entrega entrega;

    @ManyToOne
    private Receptor receptor;

    @ManyToOne
    private Donacion donacion;

}
