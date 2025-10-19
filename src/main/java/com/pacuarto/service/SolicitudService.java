package com.pacuarto.service;

import com.pacuarto.common.exceptions.NotFoundException;
import com.pacuarto.persistence.entity.Donacion;
import com.pacuarto.persistence.entity.Entrega;
import com.pacuarto.persistence.entity.Receptor;
import com.pacuarto.persistence.entity.Solicitud;
import com.pacuarto.persistence.repository.DonacionRepository;
import com.pacuarto.persistence.repository.EntregaRepository;
import com.pacuarto.persistence.repository.ReceptorRepository;
import com.pacuarto.persistence.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

        Solicitud s = new Solicitud();
        s.setFechaSolicitud(new Date());
        s.setReceptor(receptor);
        s.setDonacion(donacion);

        return solicitudRepo.save(s);
    }

    public void confirmarEntrega(Long solicitudId, Date fechaEntrega) {
        Solicitud s = solicitudRepo.findById(solicitudId)
                .orElseThrow(() -> new NotFoundException("Solicitud no encontrada: " + solicitudId));

        if (entregaRepo.existsBySolicitudId(solicitudId)) return;

        Entrega e = new Entrega();
        e.setSolicitud(s);
        e.setFechaEntrega(fechaEntrega != null ? fechaEntrega : new Date());
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
