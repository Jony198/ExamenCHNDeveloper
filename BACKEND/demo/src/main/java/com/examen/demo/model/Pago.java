package com.examen.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPago")
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "idPrestamo", nullable = false)
    private Prestamo prestamo;

    @Column(name = "montoPagado", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoPagado;

    @Column(name = "pagoCapital", nullable = false, precision = 18, scale = 2)
    private BigDecimal pagoCapital;

    @Column(name = "intereses", nullable = false, precision = 18, scale = 2)
    private BigDecimal intereses;

    @Column(name = "fechaPago", nullable = false)
    private LocalDateTime fechaPago = LocalDateTime.now();
}
