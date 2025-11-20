package com.pacuarto.web.controller;

import com.pacuarto.domain.dto.usuario.CrearUsuarioRequest;
import com.pacuarto.domain.dto.usuario.LoginResponse;
import com.pacuarto.domain.dto.usuario.LoginRequest;
import com.pacuarto.domain.model.Usuario;
import com.pacuarto.repository.UsuarioRepository;
import com.pacuarto.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;


    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) {
        return usuarioService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody CrearUsuarioRequest req) {
        Usuario creado;

        switch (req.getTipo()) {
            case DONANTE -> creado = usuarioService.registrarDonante(
                    req.getNombre(), req.getDireccion(), req.getUsername(), req.getPassword());
            case RECEPTOR -> creado = usuarioService.registrarReceptor(
                    req.getNombre(), req.getDireccion(), req.getUsername(), req.getPassword());
            default -> throw new IllegalArgumentException("Tipo no soportado");
        }

        return ResponseEntity.created(URI.create("/api/usuarios/" + creado.getId())).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> iniciarSesion(@RequestBody LoginRequest loginRequest) {

        LoginResponse response = usuarioService.autenticarYObtenerDatos(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(response);
    }
}