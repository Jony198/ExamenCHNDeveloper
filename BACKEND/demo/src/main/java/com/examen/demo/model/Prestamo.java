package com.examen.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "Prestamo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrestamo")
    private Integer idPrestamo;

    @OneToOne
    @JoinColumn(name = "idSolicitud", nullable = false, unique = true)
    private SolicitudPrestamo solicitud;

    @Column(name = "montoAprobado", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoAprobado;

    @Column(name = "fechaAprobacion", nullable = false)
    private LocalDateTime fechaAprobacion;

    @Column(name = "saldoPendiente", nullable = false, precision = 18, scale = 2)
    private BigDecimal saldoPendiente;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
}
