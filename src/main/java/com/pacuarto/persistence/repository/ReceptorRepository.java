package com.pacuarto.persistence.repository;

import com.pacuarto.persistence.entity.Receptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptorRepository extends JpaRepository<Receptor, Long> {

    boolean existsByUsername(String username);
}
