package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.DetalleDonacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//A este punto, todos los repositorios se extenderán de JpaRepository
public interface DetalleDonacionRepository extends JpaRepository <DetalleDonacion, Long>{

    List<DetalleDonacion> findByDonacionId(Long donacionId);
    List<DetalleDonacion> findByMenuId(Long menuId);

    boolean existByDonacionIdAndMenuId(Long donacionId, Long menuId);

    long deleteByDonacionId (Long donacionId);
    long deleteByMenuId (Long menuId);
}
