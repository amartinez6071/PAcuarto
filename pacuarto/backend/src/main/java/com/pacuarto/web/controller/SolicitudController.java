package com.pacuarto.web.controller;

import com.pacuarto.domain.dto.entrega.ConfirmarEntregaRequest;
import com.pacuarto.domain.dto.solicitud.CrearSolicitudRequest;
import com.pacuarto.domain.model.Solicitud;
import com.pacuarto.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    @GetMapping
    public List<Solicitud> listar(@RequestParam(value = "receptorId", required = false) Long receptorId,
                                  @RequestParam(value = "donacionId", required = false) Long donacionId) {
        if (receptorId != null) return solicitudService.listarPorReceptor(receptorId);
        if (donacionId != null) return solicitudService.listarPorDonacion(donacionId);
        return solicitudService.listar();
    }

    @PostMapping
    public ResponseEntity<Solicitud> crear(@RequestBody CrearSolicitudRequest req) {
        Solicitud s = solicitudService.crear(req.getReceptorId(), req.getDonacionId());
        return ResponseEntity.created(URI.create("/api/solicitudes/" + s.getId())).body(s);
    }

    @PostMapping("/{solicitudId}/confirmar-entrega")
    public ResponseEntity<Void> confirmarEntrega(@PathVariable Long solicitudId,
                                                 @RequestBody(required = false) ConfirmarEntregaRequest req) {
        LocalDateTime fecha = (req != null) ? req.getFechaEntrega() : null;
        solicitudService.confirmarEntrega(solicitudId, fecha);
        return ResponseEntity.noContent().build();
    }
}