package com.pacuarto.repository;

import com.pacuarto.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String q);
    boolean existsByNombreIgnoreCase(String nombre);
}
