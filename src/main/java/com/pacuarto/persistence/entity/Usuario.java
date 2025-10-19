package com.pacuarto.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_usuario",discriminatorType=DiscriminatorType.STRING)
public class Usuario{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;

    @Column(unique=true)
    private String username;
    private String password;

}
