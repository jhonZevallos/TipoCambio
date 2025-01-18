package com.tipocambio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "auditoria")
public class AuditoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String monedaOrigen;

    @Column(nullable = false)
    private String monedaDestino;

    @Column(nullable = false)
    private Double tipoCambio;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private Double montoConvertido;

    @Column(nullable = false)
    private String usuario;
    private LocalDateTime fechaOperacion;
}
