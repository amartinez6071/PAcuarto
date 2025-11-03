package com.pacuarto.domain.dto.entrega;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConfirmarEntregaRequest {
    private LocalDateTime fechaEntrega;
}