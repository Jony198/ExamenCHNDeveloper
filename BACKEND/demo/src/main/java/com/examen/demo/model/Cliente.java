package com.examen.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "nombre",nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(name = "numeroIdentificacion", nullable = false, unique = true, length = 20)
    private String numeroIdentificacion;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(length = 255)
    private String direccion;

    @Column(length = 100)
    private String correoElectronico;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
