package com.examen.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cuenta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @Column(nullable = false, length = 20)
    private String tipoCuenta; // ahorro o monetaria

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal montoApertura;

    @Column(nullable = false, length = 20)
    private String estadoCuenta = "activa";

    @Column(length = 255)
    private String motivoEstadoCuenta;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}