package com.tipocambio.service;

import com.tipocambio.model.TipoCambioModel;

public interface TipoCambioService {

    TipoCambioModel registroTipoCambio(TipoCambioModel tipoCambioModel);
    TipoCambioModel actualizarTipoCambio(TipoCambioModel tipoCambioModel);
    TipoCambioModel buscarTipoCambio(String monedaOrigen, String monedaDestino);
    Double calcularTipoCambio(String monedaOrigen, String monedaDestino, Double monto, String usuario);
}
