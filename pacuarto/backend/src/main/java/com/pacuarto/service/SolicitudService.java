package com.pacuarto.service;

import com.pacuarto.common.NotFoundException;
import com.pacuarto.domain.model.Donacion;
import com.pacuarto.domain.model.Entrega;
import com.pacuarto.domain.model.Receptor;
import com.pacuarto.domain.model.Solicitud;
import com.pacuarto.repository.DonacionRepository;
import com.pacuarto.repository.EntregaRepository;
import com.pacuarto.repository.ReceptorRepository;
import com.pacuarto.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SolicitudService {

    private final SolicitudRepository solicitudRepo;
    private final ReceptorRepository receptorRepo;
    private final DonacionRepository donacionRepo;
    private final EntregaRepository entregaRepo;

    @Transactional(readOnly = true)
    public List<Solicitud> listar() {
        return solicitudRepo.findAll();
    }

    public Solicitud crear(Long receptorId, Long donacionId) {
        Receptor receptor = receptorRepo.findById(receptorId)
                .orElseThrow(() -> new NotFoundException("Receptor no encontrado: " + receptorId));

        Donacion donacion = donacionRepo.findById(donacionId)
                .orElseThrow(() -> new NotFoundException("Donación no encontrada: " + donacionId));

        if (solicitudRepo.existsByReceptorIdAndDonacionId(receptorId, donacionId)) {
            throw new IllegalStateException("El receptor ya tiene una solicitud para esta donación");
        }

        Solicitud s = new Solicitud();
        s.setFechaSolicitud(LocalDateTime.now());
        s.setReceptor(receptor);
        s.setDonacion(donacion);
        return solicitudRepo.save(s);
    }

    public void confirmarEntrega(Long solicitudId, LocalDateTime fechaEntrega) {
        Solicitud s = solicitudRepo.findById(solicitudId)
                .orElseThrow(() -> new NotFoundException("Solicitud no encontrada: " + solicitudId));

        if (entregaRepo.existsBySolicitudId(solicitudId)) return;

        Entrega e = new Entrega();
        e.setSolicitud(s);
        e.setFechaEntrega(fechaEntrega != null ? fechaEntrega : LocalDateTime.now());
        entregaRepo.save(e);
    }

    @Transactional(readOnly = true)
    public List<Solicitud> listarPorReceptor(Long receptorId) {
        return solicitudRepo.findByReceptorId(receptorId);
    }

    @Transactional(readOnly = true)
    public List<Solicitud> listarPorDonacion(Long donacionId) {
        return solicitudRepo.findByDonacionId(donacionId);
    }
}
