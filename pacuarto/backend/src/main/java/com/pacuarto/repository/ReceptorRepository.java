package com.pacuarto.repository;

import com.pacuarto.domain.model.Receptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptorRepository extends JpaRepository <Receptor, Long> {
}
