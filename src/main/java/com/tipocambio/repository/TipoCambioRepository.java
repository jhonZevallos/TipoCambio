package com.tipocambio.repository;

import com.tipocambio.model.TipoCambioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambioModel, Long> {

    TipoCambioModel findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
