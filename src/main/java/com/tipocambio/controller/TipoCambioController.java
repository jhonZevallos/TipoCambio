package com.tipocambio.controller;

import com.tipocambio.model.TipoCambioModel;
import com.tipocambio.service.TipoCambioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipoCambio")
public class TipoCambioController {

    private TipoCambioService tipoCambioService;

    public TipoCambioController(TipoCambioService tipoCambioService) {
        this.tipoCambioService = tipoCambioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarTipoCambio(@RequestBody TipoCambioModel tipoCambioModel) {
        try {
            return ResponseEntity.ok(tipoCambioService.registroTipoCambio(tipoCambioModel));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarTipoCambio(@RequestBody TipoCambioModel tipoCambioModel) {
        try {
            return ResponseEntity.ok(tipoCambioService.actualizarTipoCambio(tipoCambioModel));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarTipoCambio(@RequestParam String monedaOrigen, @RequestParam String monedaDestino) {
        try {
            return ResponseEntity.ok(tipoCambioService.buscarTipoCambio(monedaOrigen, monedaDestino));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/calcular")
    public ResponseEntity<?> calcularTipoCambio(@RequestParam String monedaOrigen,
                                                @RequestParam String monedaDestino,
                                                @RequestParam Double monto,
                                                @RequestParam String usuario) {
        try {
            return ResponseEntity.ok(tipoCambioService.calcularTipoCambio(monedaOrigen, monedaDestino, monto, usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
