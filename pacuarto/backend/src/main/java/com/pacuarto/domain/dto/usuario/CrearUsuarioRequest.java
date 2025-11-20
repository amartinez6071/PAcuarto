package com.pacuarto.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearUsuarioRequest {

    @NotBlank private String nombre;
    private String direccion;
    @NotBlank private String username;
    @NotBlank private String password;

    @NotNull
    private Tipo tipo; // DONANTE o RECEPTOR

    public enum Tipo { DONANTE, RECEPTOR }
}
