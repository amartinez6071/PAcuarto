package com.pacuarto.web.controller;

import com.pacuarto.domain.dto.menu.ActualizarMenuDetalleRequest;
import com.pacuarto.domain.dto.menu.CrearMenuRequest;
import com.pacuarto.domain.model.Menu;
import com.pacuarto.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public List<Menu> listar(@RequestParam(value = "q", required = false) String q,
                             @RequestParam(value = "donanteId", required = false) Long donanteId,
                             @RequestParam(value = "productoId", required = false) Long productoId) {

        if (donanteId != null) return menuService.listarPorDonante(donanteId);
        if (productoId != null) return menuService.listarConProducto(productoId);
        if (q != null && !q.isBlank()) return menuService.listar()
                .stream().filter(m -> m.getDetalle() != null && m.getDetalle().toLowerCase().contains(q.toLowerCase()))
                .toList();
        return menuService.listar();
    }

    @GetMapping("/{id}")
    public Menu obtener(@PathVariable Long id) {
        return menuService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<Menu> crear(@RequestBody CrearMenuRequest req) {
        Menu creado = menuService.crear(req.getDonanteId(), req.getDetalle());
        return ResponseEntity.created(URI.create("/api/menus/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}/detalle")
    public Menu actualizarDetalle(@PathVariable Long id, @RequestBody ActualizarMenuDetalleRequest req) {
        return menuService.actualizarDetalle(id, req.getDetalle());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        menuService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // relaciones menu <-> producto
    @PostMapping("/{menuId}/productos/{productoId}")
    public ResponseEntity<Void> agregarProducto(@PathVariable Long menuId, @PathVariable Long productoId) {
        menuService.agregarProducto(menuId, productoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{menuId}/productos/{productoId}")
    public ResponseEntity<Void> quitarProducto(@PathVariable Long menuId, @PathVariable Long productoId) {
        menuService.quitarProducto(menuId, productoId);
        return ResponseEntity.noContent().build();
    }
}
