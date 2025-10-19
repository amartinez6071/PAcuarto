package com.pacuarto.service;

import com.pacuarto.common.exceptions.NotFoundException;
import com.pacuarto.persistence.entity.DetalleMenu;
import com.pacuarto.persistence.entity.Donante;
import com.pacuarto.persistence.entity.Menu;
import com.pacuarto.persistence.entity.Producto;
import com.pacuarto.persistence.repository.DetalleMenuRepository;
import com.pacuarto.persistence.repository.DonanteRepository;
import com.pacuarto.persistence.repository.MenuRepository;
import com.pacuarto.persistence.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

    private final MenuRepository menuRepo;
    private final DonanteRepository donanteRepo;
    private final DetalleMenuRepository detalleMenuRepo;
    private final ProductoRepository productoRepo;


    @Transactional(readOnly = true)
    public List<Menu> listar(){
        return menuRepo.findAll();
    }

    public Menu obtener (Long id){
        return menuRepo.findById(id)
                .orElseThrow(()->new NotFoundException("Menú no encontrado: "+id));
    }

    public Menu crear (Long donanteId, String detalle){
        Donante donante = donanteRepo.findById(donanteId)
                .orElseThrow(()-> new NotFoundException("Donante no encontrado: "+donanteId));

        Menu menu = new Menu();
        menu.setDonante(donante);
        menu.setDetalle(detalle);
        return menuRepo.save(menu);
    }

    public Menu actualizarDetalle (Long id, String nuevoDetalle){
        Menu m = obtener(id);
        m.setDetalle(nuevoDetalle);
        return menuRepo.save(m);
    }

    public void eliminar (Long id){
        detalleMenuRepo.deleteByMenuId(id);
        menuRepo.deleteById(id);
    }

    public void agregarProducto (Long menuId, Long productoId){
        Menu menu = obtener(menuId);
        Producto producto = productoRepo.findById(productoId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + productoId));

        boolean existe = detalleMenuRepo.existsByMenuIdAndProductId(menuId, productoId);
        if (existe) return;

        DetalleMenu det = new DetalleMenu();
        det.setMenu(menu);
        det.setProducto(producto);
        detalleMenuRepo.save(det);

    }

    public void quitarProducto (Long menuId, Long productoId){
        detalleMenuRepo.deleteByProductoIdAndMenuId(menuId, productoId);

        detalleMenuRepo.findByMenuId(menuId).stream()
                .filter(dm -> dm.getProducto().getId().equals(productoId))
                .findFirst()
                .ifPresent(detalleMenuRepo::delete);
    }

    @Transactional(readOnly = true)
    public List<Menu>listarPorDonante(Long donanteId){
        return menuRepo.findByDonanteId(donanteId);
    }

    public List<Menu> listarConProducto(Long productoId){
        return menuRepo.findByDetalleProductosProductoId(productoId);
    }
}
