package com.pacuarto.web.controller;

import com.pacuarto.domain.dto.menu.AgregarMenuADonacionRequest;
import com.pacuarto.domain.model.Donacion;
import com.pacuarto.domain.model.Menu;
import com.pacuarto.service.DonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/donaciones")
@RequiredArgsConstructor
public class DonacionController {

    private final DonacionService donacionService;

    @GetMapping
    public List<Donacion> listar() {
        return donacionService.listar();
    }

    @GetMapping("/{id}")
    public Donacion obtener(@PathVariable Long id) {
        return donacionService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<Donacion> crear(@RequestBody Donacion d) {
        Donacion creada = donacionService.crear(d);
        return ResponseEntity.created(URI.create("/api/donaciones/" + creada.getId())).body(creada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        donacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{donacionId}/menus")
    public ResponseEntity<Void> agregarMenu(@PathVariable Long donacionId, @RequestBody AgregarMenuADonacionRequest req) {
        donacionService.agregarMenu(donacionId, req.getMenuId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{donacionId}/menus/{menuId}")
    public ResponseEntity<Void> quitarMenu(@PathVariable Long donacionId, @PathVariable Long menuId) {
        donacionService.quitarMenu(donacionId, menuId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{donacionId}/menus")
    public List<Menu> listarMenus(@PathVariable Long donacionId) {
        return donacionService.listarMenus(donacionId);
    }
}