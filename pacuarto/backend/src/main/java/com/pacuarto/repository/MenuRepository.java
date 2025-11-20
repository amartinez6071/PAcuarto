package com.pacuarto.repository;

import com.pacuarto.domain.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByDonanteId(Long donanteId);
    List<Menu> findByDetalleDonacionesDonacionId(Long donacionId);
    List<Menu> findByDetalleProductosProductoId(Long productoId);
    List<Menu> findByDetalleContainingIgnoreCase(String q);

}
