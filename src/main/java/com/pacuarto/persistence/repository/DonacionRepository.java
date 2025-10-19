package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {

    boolean existsBySolicituderReceptorId(Long receptorId);
    long countBySolicitudesReceptorId(Long receptorId);
}
