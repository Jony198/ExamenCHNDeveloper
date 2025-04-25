package com.examen.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cheque")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCheque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idChequera", nullable = false)
    private Chequera chequera;

    @Column(nullable = false)
    private Integer numeroCheque;

    @Column(nullable = false, length = 20)
    private String estadoCheque = "emitido";

    @Column(length = 255)
    private String motivoEstadoCheque;

    @Column(precision = 18, scale = 2)
    private BigDecimal monto;

    private LocalDateTime fechaMovimiento;
}
