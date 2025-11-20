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
public class Donante extends Usuario {

    // Relación 1:N con Donacion (Requerimiento)
    @OneToMany(mappedBy = "donante")
    private List<Donacion> donaciones;
}
