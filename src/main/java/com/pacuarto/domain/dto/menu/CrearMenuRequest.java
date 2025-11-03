package com.pacuarto.domain.dto.menu;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearMenuRequest {
    private Long donanteId;
    private String detalle;
}