package com.pacuarto.domain.dto.solicitud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearSolicitudRequest {
    private Long receptorId;
    private Long donacionId;
}
