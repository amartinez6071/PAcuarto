package com.pacuarto.repository;

import com.pacuarto.domain.model.DetalleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleMenuRepository extends JpaRepository<DetalleMenu, Long> {

    List<DetalleMenu> findByMenuId(Long menuId);
    List<DetalleMenu> findByProductoId (Long productoId);

    boolean existsByMenuIdAndProductoId(Long menuId, Long productoId);

    long deleteByMenuId(Long menuId);
    long deleteByProductoId(Long productoId);
    long deleteByProductoIdAndMenuId(Long productoId, Long menuId);

}
