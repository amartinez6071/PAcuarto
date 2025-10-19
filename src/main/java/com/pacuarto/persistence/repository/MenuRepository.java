package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByDonanteId (Long donanteId);
    List<Menu> findByDetalleDonacionesDonacionId (Long donacionId);
    List<Menu> findByDetalleProductosProductoId (Long productoId);
    List<Menu> findByDetalleContainingIgnoreCase(String q);

}
