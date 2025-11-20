package com.pacuarto.service;

import com.pacuarto.common.NotFoundException;
import com.pacuarto.domain.model.DetalleDonacion;
import com.pacuarto.domain.model.Donacion;
import com.pacuarto.domain.model.Menu;
import com.pacuarto.repository.DetalleDonacionRepository;
import com.pacuarto.repository.DonacionRepository;
import com.pacuarto.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DonacionService {

    private final DonacionRepository donacionRepo;
    private final MenuRepository menuRepo;
    private final DetalleDonacionRepository detalleDonacionRepo;

    @Transactional(readOnly = true)
    public List<Donacion> listar() {
        return donacionRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Donacion obtener(Long id) {
        return donacionRepo.findById(id).orElseThrow(() -> new NotFoundException("Donación no encontrada: " + id));
    }

    public Donacion crear(Donacion d) {
        return donacionRepo.save(d);
    }

    public void eliminar(Long id) {
        detalleDonacionRepo.deleteByDonacionId(id);
        donacionRepo.deleteById(id);
    }

    public void agregarMenu(Long donacionId, Long menuId) {
        Donacion donacion = donacionRepo.findById(donacionId)
                .orElseThrow(() -> new NotFoundException("Donación no encontrada: " + donacionId));


        Menu menu = menuRepo.findById(menuId)
                .orElseThrow(() -> new NotFoundException("Menú no encontrado: " + menuId));

        boolean existe = detalleDonacionRepo.existsByDonacionIdAndMenuId(donacionId, menuId);
        if (existe) return;

        DetalleDonacion dd = new DetalleDonacion();
        dd.setDonacion(donacion);
        dd.setMenu(menu);
        detalleDonacionRepo.save(dd);
    }

    public void quitarMenu(Long donacionId, Long menuId) {
        detalleDonacionRepo.deleteByDonacionIdAndMenuId(donacionId, menuId);
    }

    @Transactional(readOnly = true)
    public List<Menu> listarMenus(Long donacionId) {
        return menuRepo.findByDetalleDonacionesDonacionId(donacionId);
    }
}
