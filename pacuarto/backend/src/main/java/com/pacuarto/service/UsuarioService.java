package com.pacuarto.service;

import com.pacuarto.common.NotFoundException;
import com.pacuarto.domain.dto.usuario.LoginResponse;
import com.pacuarto.domain.model.Donante;
import com.pacuarto.domain.model.Receptor;
import com.pacuarto.domain.model.Usuario;
import com.pacuarto.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

    private static final String UNAUTHORIZED_MESSAGE = "Credenciales inválidas";

    public UsuarioService (UsuarioRepository usuarioRepo){
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario obtener(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + id));
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + username));
    }

    public Usuario registrar(Usuario u) {
        if (usuarioRepo.existsByUsername(u.getUsername())) {
            throw new IllegalStateException("El username ya está en uso");
        }
        return usuarioRepo.save(u);
    }

    public Donante registrarDonante(String nombre, String direccion, String username, String password) {
        Donante d = new Donante();
        d.setNombre(nombre);
        d.setDireccion(direccion);
        d.setUsername(username);
        d.setPassword(password);
        return (Donante) registrar(d);
    }

    public Receptor registrarReceptor(String nombre, String direccion, String username, String password) {
        Receptor r = new Receptor();
        r.setNombre(nombre);
        r.setDireccion(direccion);
        r.setUsername(username);
        r.setPassword(password);
        return (Receptor) registrar(r);
    }

    public void eliminar(Long id) {
        usuarioRepo.deleteById(id);
    }


    public LoginResponse autenticarYObtenerDatos(String username, String password){


        Usuario usuario = usuarioRepo.findByUsername(username).orElseThrow(()->
                new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, UNAUTHORIZED_MESSAGE
                )
        );

        if (!usuario.getPassword().equals(password)){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    UNAUTHORIZED_MESSAGE
            );
        }

        String tipoUsuario;
        if (usuario instanceof Donante) {
            tipoUsuario = "DONANTE";
        } else if (usuario instanceof Receptor) {
            tipoUsuario = "RECEPTOR";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Tipo de usuario desconocido o inválido en el sistema."
            );
        }

        return new LoginResponse(
                usuario.getId(),
                usuario.getUsername(),
                "fake-jwt-token-" + usuario.getId(),
                tipoUsuario
        );
    }
}