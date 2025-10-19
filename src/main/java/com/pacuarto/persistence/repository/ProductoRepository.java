package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository <Producto, Long>{

    List<Producto> findByNombreContainingIgnoreCase (String q);
    boolean existisByNombreIgnoreCase(String nombre);
}
