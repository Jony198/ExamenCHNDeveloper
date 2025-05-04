package com.examen.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "numeroIdentificacion", nullable = false, length = 20, unique = true)
    private String numeroIdentificacion;

    @Column(name = "fechaNacimiento", nullable = false)
    private java.time.LocalDate fechaNacimiento;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "correoElectronico", length = 100)
    private String correoElectronico;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fechaRegistro", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime fechaRegistro;
}
