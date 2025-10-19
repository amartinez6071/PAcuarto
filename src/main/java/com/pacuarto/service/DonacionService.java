package com.pacuarto.service;

import com.pacuarto.common.exceptions.NotFoundException;
import com.pacuarto.persistence.entity.DetalleDonacion;
import com.pacuarto.persistence.entity.Donacion;
import com.pacuarto.persistence.entity.Menu;
import com.pacuarto.persistence.repository.DetalleDonacionRepository;
import com.pacuarto.persistence.repository.DonacionRepository;
import com.pacuarto.persistence.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DonacionService {

    private final DonacionRepository donacionRepo;
    private final MenuRepository menuRepo;
    private final DetalleDonacionRepository detalleDonacionRepo;


    @Transactional(readOnly = true)
    public List<Donacion> listar(){
        return donacionRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Donacion obtener(Long id){
        return donacionRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("Donación no encontrada: "+id));
    }

    public Donacion crear(Donacion d){
        return donacionRepo.save(d);
    }

    public Donacion actualizar (Long id, Donacion datos){

        Donacion existente = obtener(id);

        existente.setDetalleMenus(datos.getDetalleMenus());

        return donacionRepo.save(existente);

    }

    public void eliminar(Long id){
        detalleDonacionRepo.deleteByDonacionId(id);
        donacionRepo.deleteById(id);
    }

    public void agregarMenuALaDonacion (Long donacionId, Long menuId){
        Donacion donacion = obtener(donacionId);
        Menu menu = menuRepo.findById(menuId)
                .orElseThrow(()->new NotFoundException("Menú no encontrado: "+menuId));

        boolean existe = detalleDonacionRepo.existByDonacionIdAndMenuId(donacionId, menuId);
        if (existe) return;

        DetalleDonacion det = new DetalleDonacion();
        det.setDonacion(donacion);
        det.setMenu(menu);
        detalleDonacionRepo.save(det);
    }

    public void quitarMenuDeLaDonacion(Long donacionId, Long menuId){
        detalleDonacionRepo.deleteByDonacionId(donacionId);
    }

    public List<Menu> listarMenusDeDonacion (Long donacionId){
        return menuRepo.findByDetalleDonacionesDonacionId(donacionId);
    }
}

