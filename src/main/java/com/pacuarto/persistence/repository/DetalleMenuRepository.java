package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.DetalleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleMenuRepository extends JpaRepository <DetalleMenu, Long>{

    List<DetalleMenu> findByMenuId(Long menuId);
    List<DetalleMenu>findByProductoId(Long productoId);

    boolean existsByMenuIdAndProductId(Long menuId, Long productoId);

    long deleteByMenuId(Long menuId);
    long deleteByProductoId(Long productiId);
    long deleteByProductoIdAndMenuId(Long menuId, Long productoId);

}
