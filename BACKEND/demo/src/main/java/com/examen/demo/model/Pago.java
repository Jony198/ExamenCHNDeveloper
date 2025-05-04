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

    @Column(name = "montoPagado", nullable = false)
    private BigDecimal montoPagado;

    @Column(name = "fechaPago", nullable = false)
    private LocalDateTime fechaPago = LocalDateTime.now();
}
