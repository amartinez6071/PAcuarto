package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EntregaRepository extends JpaRepository <Entrega, Long>{

    Optional<Entrega> findBySolicitudId(Long solicitudId);
    boolean existsBySolicitudId(Long solicitudId);
}
