package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SolicitudRepository extends JpaRepository <Solicitud, Long>{

    List<Solicitud>findByReceptorId(Long receptorId);
    List<Solicitud>findByDonacionId(Long donacionId);
    List<Solicitud>findByFechaSolicitudBetween(Date desde, Date hasta);
    boolean existsByReceptorIdAndDonacionId (Long receptorId, Long donacionId);
}
