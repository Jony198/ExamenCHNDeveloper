package com.examen.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Chequera")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chequera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChequera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCuenta", nullable = false)
    private Cuenta cuenta;

    @Column(nullable = false)
    private Integer cantidadCheques;

    @Column(nullable = false, length = 20)
    private String estadoChequera = "activa";

    @Column(length = 255)
    private String motivoEstadoChequera;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
