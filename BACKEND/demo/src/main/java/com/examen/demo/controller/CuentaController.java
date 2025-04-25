package com.examen.demo.controller;

import com.examen.demo.model.Cuenta;
import com.examen.demo.response.CuentaResponse;
import com.examen.demo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin(origins = "*")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/cliente/{idCliente}")
    public CuentaResponse listarPorCliente(@PathVariable Integer idCliente) {
        return cuentaService.listarPorClienteId(idCliente);
    }

    @PostMapping
    public CuentaResponse crearCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.guardar(cuenta);
    }

    @PutMapping("/{idCuenta}")
    public CuentaResponse actualizarCuenta(@PathVariable Integer idCuenta, @RequestBody Cuenta cuenta) {
        cuenta.setIdCuenta(idCuenta); // Aseguramos el ID desde el path
        return cuentaService.actualizar(cuenta);
    }

    @DeleteMapping("/{idCuenta}")
    public CuentaResponse eliminarCuenta(@PathVariable Integer idCuenta) {
        return cuentaService.eliminar(idCuenta);
    }
}
