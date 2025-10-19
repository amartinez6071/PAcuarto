package com.pacuarto.service;

import com.pacuarto.common.exceptions.NotFoundException;
import com.pacuarto.persistence.entity.Usuario;
import com.pacuarto.persistence.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

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
}
