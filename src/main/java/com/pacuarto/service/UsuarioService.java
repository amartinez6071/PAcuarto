package com.pacuarto.service;

import com.pacuarto.common.NotFoundException;
import com.pacuarto.domain.model.Donante;
import com.pacuarto.domain.model.Receptor;
import com.pacuarto.domain.model.Usuario;
import com.pacuarto.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

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
}