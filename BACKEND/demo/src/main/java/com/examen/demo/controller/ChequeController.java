package com.examen.demo.controller;

import com.examen.demo.model.Cheque;
import com.examen.demo.response.ChequeResponse;
import com.examen.demo.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cheques")
@CrossOrigin(origins = "*")
public class ChequeController {

    @Autowired
    private ChequeService chequeService;

    @GetMapping("/chequera/{idChequera}")
    public ChequeResponse listarPorChequera(@PathVariable Integer idChequera) {
        return chequeService.listarPorChequeraId(idChequera);
    }

    @PostMapping
    public ChequeResponse crearCheque(@RequestBody Cheque cheque) {
        return chequeService.guardar(cheque);
    }

    @PutMapping("/{idCheque}")
    public ChequeResponse actualizarCheque(@PathVariable Integer idCheque, @RequestBody Cheque cheque) {
        cheque.setIdCheque(idCheque); // Asegura que el ID venga del path
        return chequeService.actualizar(cheque);
    }

    @DeleteMapping("/{idCheque}")
    public ChequeResponse eliminarCheque(@PathVariable Integer idCheque) {
        return chequeService.eliminar(idCheque);
    }
}
