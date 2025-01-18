package com.tipocambio.service.impl;

import static com.tipocambio.utils.GeneralConstant.NOT_FOUND_TIPOCAMBIO;

import com.tipocambio.model.AuditoriaModel;
import com.tipocambio.model.TipoCambioModel;
import com.tipocambio.repository.AuditoriaRepository;
import com.tipocambio.repository.TipoCambioRepository;
import com.tipocambio.service.TipoCambioService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

    private final TipoCambioRepository tipoCambioRepository;
    private final AuditoriaRepository auditoriaRepository;

    public TipoCambioServiceImpl(TipoCambioRepository tipoCambioRepository, AuditoriaRepository auditoriaRepository) {
        this.tipoCambioRepository = tipoCambioRepository;
        this.auditoriaRepository = auditoriaRepository;
    }

    @Override
    public TipoCambioModel registroTipoCambio(TipoCambioModel tipoCambioModel) {
        tipoCambioModel.setFechaCreacion(LocalDateTime.now());
        return tipoCambioRepository.save(tipoCambioModel);
    }

    @Override
    public TipoCambioModel actualizarTipoCambio(TipoCambioModel tipoCambioModel) {
        return tipoCambioRepository.findById(tipoCambioModel.getId())
                .map(tc -> {
                        tc.setMonedaOrigen(tipoCambioModel.getMonedaOrigen());
                        tc.setMonedaDestino(tipoCambioModel.getMonedaDestino());
                        tc.setTipoCambio(tipoCambioModel.getTipoCambio());
                        tc.setUsuarioModificacion(tipoCambioModel.getUsuarioModificacion());
                        tc.setFechaModificacion(LocalDateTime.now());
                        return tipoCambioRepository.save(tc);
                })
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_TIPOCAMBIO));
    }

    @Override
    public TipoCambioModel buscarTipoCambio(String monedaOrigen, String monedaDestino) {
        TipoCambioModel tipoCambioModel =  tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino);
        if (tipoCambioModel == null) throw new RuntimeException(NOT_FOUND_TIPOCAMBIO);
        return tipoCambioModel;
    }

    @Override
    public Double calcularTipoCambio(String monedaOrigen, String monedaDestino, Double monto, String usuario) {
        TipoCambioModel tipoCambioModel = this.buscarTipoCambio(monedaOrigen, monedaDestino);
        var montoConvertido = tipoCambioModel.getTipoCambio() * monto;
        this.registrarAuditoria(AuditoriaModel.builder()
                .monedaOrigen(monedaOrigen)
                .monedaDestino(monedaDestino)
                .monto(monto)
                .tipoCambio(tipoCambioModel.getTipoCambio())
                .montoConvertido(montoConvertido)
                .usuario(usuario)
                .fechaOperacion(LocalDateTime.now())
                .build());
        return montoConvertido;
    }

    void registrarAuditoria(AuditoriaModel auditoriaModel) {
        auditoriaRepository.save(auditoriaModel);
    }
}
