package com.pacuarto.repository;

import com.pacuarto.domain.model.DetalleDonacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleDonacionRepository extends JpaRepository<DetalleDonacion, Long> {

    List<DetalleDonacion> findByDonacionId(Long donacionId);
    List<DetalleDonacion> findByMenuId(Long menuId);

    boolean existsByDonacionIdAndMenuId(Long donacionId, Long menuId);

    long deleteByDonacionIdAndMenuId(Long donacionId, Long menuId);
    long deleteByDonacionId(Long donacionId);
    long deleteByMenuId(Long menuId);

}
