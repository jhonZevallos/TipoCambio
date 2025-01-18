package com.tipocambio.repository;

import com.tipocambio.model.AuditoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<AuditoriaModel, Long> {
}
