package com.examen.demo.controller;

import com.examen.demo.model.SolicitudPrestamo;
import com.examen.demo.response.SolicitudPrestamoResponse;
import com.examen.demo.service.SolicitudPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudPrestamoController {

    @Autowired
    private SolicitudPrestamoService solicitudPrestamoService;

    @GetMapping
    public SolicitudPrestamoResponse listarSolicitudes() {
        return solicitudPrestamoService.listarTodos();
    }

    @GetMapping("/{id}")
    public SolicitudPrestamoResponse obtenerPorId(@PathVariable Integer id) {
        return solicitudPrestamoService.obtenerPorId(id);
    }

    @PostMapping
    public SolicitudPrestamoResponse crearSolicitud(@RequestBody SolicitudPrestamo solicitud) {
        return solicitudPrestamoService.guardar(solicitud);
    }

    @PutMapping("/estado")
    public SolicitudPrestamoResponse actualizarEstado(
            @RequestBody SolicitudPrestamo solicitud
    ) {
        return solicitudPrestamoService.actualizarEstado(solicitud);
    }
}
