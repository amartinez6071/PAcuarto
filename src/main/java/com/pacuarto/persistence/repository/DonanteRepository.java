package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.Donante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonanteRepository extends JpaRepository <Donante, Long>{

    boolean existsByUsername(String username);
}
