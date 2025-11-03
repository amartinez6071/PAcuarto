package com.pacuarto.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearUsuarioRequest {

    @NotBlank private String nombre;
    private String direccion;
    @NotBlank private String username;
    @NotBlank private String password;

    @NotBlank
    private Tipo tipo; // DONANTE o RECEPTOR

    public enum Tipo { DONANTE, RECEPTOR }
}
