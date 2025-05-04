package com.examen.demo.controller;

import com.examen.demo.model.Prestamo;
import com.examen.demo.response.PrestamoResponse;
import com.examen.demo.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public PrestamoResponse listarPrestamos() {
        return prestamoService.listarTodos();
    }

    @GetMapping("/{id}")
    public PrestamoResponse obtenerPrestamoPorId(@PathVariable Integer id) {
        return prestamoService.obtenerPorId(id);
    }

    @PostMapping
    public PrestamoResponse crearPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.guardar(prestamo);
    }

    @PutMapping("/{id}")
    public PrestamoResponse actualizarPrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        prestamo.setIdPrestamo(id);
        return prestamoService.actualizar(prestamo);
    }

    @DeleteMapping("/{id}")
    public PrestamoResponse eliminarPrestamo(@PathVariable Integer id) {
        return prestamoService.eliminar(id);
    }
}
