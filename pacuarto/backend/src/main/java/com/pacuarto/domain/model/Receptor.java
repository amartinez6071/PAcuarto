package com.pacuarto.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Receptor extends Usuario {

    // Relación 1:N con Solicitud (ya existía en Solicitud)
    @OneToMany(mappedBy = "receptor")
    private List<Solicitud> solicitudes;
}