package com.pacuarto.persistence.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("RECEPTOR")
public class Receptor extends Usuario{

    @OneToMany(mappedBy="receptor",fetch=FetchType.LAZY)
    private List<Solicitud>solicitudes;
}
