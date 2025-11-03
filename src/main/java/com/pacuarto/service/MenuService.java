package com.pacuarto.service;

import com.pacuarto.common.NotFoundException;
import com.pacuarto.domain.model.DetalleMenu;
import com.pacuarto.domain.model.Donante;
import com.pacuarto.domain.model.Menu;
import com.pacuarto.domain.model.Producto;
import com.pacuarto.repository.DetalleMenuRepository;
import com.pacuarto.repository.DonanteRepository;
import com.pacuarto.repository.MenuRepository;
import com.pacuarto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
@Transactional
public class MenuService {

    private final MenuRepository menuRepo;
    private final DonanteRepository donanteRepo;
    private final DetalleMenuRepository detalleMenuRepo;
    private final ProductoRepository productoRepo;

    @Transactional(readOnly = true)
    public List<Menu> listar() {
        return menuRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Menu obtener(Long id) {
        return menuRepo.findById(id).orElseThrow(() -> new NotFoundException("Menú no encontrado: " + id));
    }

    public Menu crear(Long donanteId, String detalle) {
        Donante d = donanteRepo.findById(donanteId).orElseThrow(() -> new NotFoundException("Donante no encontrado: " + donanteId));
        Menu m = new Menu();
        m.setDonante(d);
        m.setDetalle(detalle);
        return menuRepo.save(m);
    }

    public Menu actualizarDetalle(Long id, String nuevoDetalle) {
        Menu m = obtener(id);
        m.setDetalle(nuevoDetalle);
        return menuRepo.save(m);
    }

    public void eliminar(Long id) {
        detalleMenuRepo.deleteByMenuId(id);
        menuRepo.deleteById(id);
    }

    public void agregarProducto(Long menuId, Long productoId) {
        Menu menu = obtener(menuId);
        Producto prod = productoRepo.findById(productoId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + productoId));

        boolean existe = detalleMenuRepo.existsByMenuIdAndProductoId(menuId, productoId);
        if (existe) return;

        DetalleMenu dm = new DetalleMenu();
        dm.setMenu(menu);
        dm.setProducto(prod);
        detalleMenuRepo.save(dm);
    }

    public void quitarProducto(Long menuId, Long productoId) {
        detalleMenuRepo.deleteByProductoIdAndMenuId(productoId, menuId);
    }

    @Transactional(readOnly = true)
    public List<Menu> listarPorDonante(Long donanteId) {
        return menuRepo.findByDonanteId(donanteId);
    }

    @Transactional(readOnly = true)
    public List<Menu> listarConProducto(Long productoId) {
        return menuRepo.findByDetalleProductosProductoId(productoId);
    }
}
