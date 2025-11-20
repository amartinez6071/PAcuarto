package com.pacuarto.repository;

import com.pacuarto.domain.model.Receptor;
import com.pacuarto.domain.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    List<Solicitud> findByReceptorId(Long receptorId);
    List<Solicitud> findByDonacionId(Long donacionId);
    //List<Solicitud> findBySolicitudId(Long solicitudId);
    List<Solicitud> findByFechaSolicitudBetween(LocalDateTime desde, LocalDateTime hasta);

    boolean existsByReceptorIdAndDonacionId(Long receptorId, Long donacionId);

}
