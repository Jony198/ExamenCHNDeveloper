package com.examen.demo.controller;

import com.examen.demo.model.Pago;
import com.examen.demo.response.PagoResponse;
import com.examen.demo.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*") // Puedes ajustarlo a tu dominio frontend si lo necesitas
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public PagoResponse listarPagos() {
        return pagoService.listarTodos();
    }

    @GetMapping("/{id}")
    public PagoResponse obtenerPagoPorId(@PathVariable Integer id) {
        return pagoService.obtenerPorId(id);
    }

    @PostMapping
    public PagoResponse crearPago(@RequestBody Pago pago) {
        return pagoService.guardar(pago);
    }

    @GetMapping("/historial/{idPrestamo}")
    public PagoResponse obtenerHistorial(@PathVariable Integer idPrestamo) {
        return pagoService.getHistorialPagos(idPrestamo);
    }

}
