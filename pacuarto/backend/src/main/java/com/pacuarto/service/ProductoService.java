package com.pacuarto.service;

import com.pacuarto.common.NotFoundException;
import com.pacuarto.domain.model.Producto;
import com.pacuarto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepo;

    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return productoRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Producto obtener(Long id) {
        return productoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + id));
    }

    public Producto crear(Producto p) {
        if (productoRepo.existsByNombreIgnoreCase(p.getNombre())) {
            throw new IllegalStateException("Ya existe un producto con ese nombre");
        }
        return productoRepo.save(p);
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto p = obtener(id);
        p.setNombre(datos.getNombre());
        p.setCantidad(datos.getCantidad());
        return productoRepo.save(p);
    }

    public void eliminar(Long id) {
        productoRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Producto> buscar(String q) {
        return productoRepo.findByNombreContainingIgnoreCase(q);
    }
}
