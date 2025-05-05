package com.examen.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "SolicitudPrestamo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudPrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSolicitud")
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;

    @Column(name = "plazoEnMeses", nullable = false)
    private Integer plazoEnMeses;

    @Column(name = "tasaInteres", nullable = false)
    private double tasaInteres;

    @Column(name = "fechaSolicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "motivoRechazo")
    private String motivoRechazo;
}
