package com.pacuarto.repository;

import com.pacuarto.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Optional<Entrega> findBySolicitudId(Long solicitudId);

    boolean existsBySolicitudId(Long solicitudId);
    long deleteBySolicitudId(Long solicitudId);

}
