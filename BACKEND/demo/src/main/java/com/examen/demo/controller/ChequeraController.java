package com.examen.demo.controller;

import com.examen.demo.model.Chequera;
import com.examen.demo.response.ChequeraResponse;
import com.examen.demo.service.ChequeraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chequeras")
@CrossOrigin(origins = "*")
public class ChequeraController {

    @Autowired
    private ChequeraService chequeraService;

    @GetMapping("/cuenta/{idCuenta}")
    public ChequeraResponse listarPorCuenta(@PathVariable Integer idCuenta) {
        return chequeraService.listarPorCuentaId(idCuenta);
    }

    @PostMapping
    public ChequeraResponse crearChequera(@RequestBody Chequera chequera) {
        return chequeraService.guardar(chequera);
    }

    @PutMapping("/{idChequera}")
    public ChequeraResponse actualizarChequera(@PathVariable Integer idChequera, @RequestBody Chequera chequera) {
        chequera.setIdChequera(idChequera); // Asegura que el ID viene del path
        return chequeraService.actualizar(chequera);
    }

    @DeleteMapping("/{idChequera}")
    public ChequeraResponse eliminarChequera(@PathVariable Integer idChequera) {
        return chequeraService.eliminar(idChequera);
    }
}
