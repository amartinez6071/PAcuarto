package com.pacuarto.repository;

import com.pacuarto.domain.model.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionRepository extends JpaRepository <Donacion, Long>{

    boolean existsBySolicitudesReceptorId(Long receptorId);
    long countBySolicitudesReceptorId(Long receptorId);

}
