package com.examen.demo.service;

import com.examen.demo.entity.ErrorEntity;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Cheque;
import com.examen.demo.model.Chequera;
import com.examen.demo.repository.ChequeraRepository;
import com.examen.demo.repository.ChequeRepository;
import com.examen.demo.response.ChequeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChequeService {

    @Autowired
    private ChequeRepository chequeRepository;

    @Autowired
    private ChequeraRepository chequeraRepository;

    private static final String INSTANCE = "ChequeService";

    public ChequeResponse listarPorChequeraId(Integer idChequera) {
        ErrorList errores = new ErrorList();
        try {
            if (!chequeraRepository.existsById(idChequera)) {
                errores.addError(new ErrorEntity("Chequera no encontrada"), "listarPorChequeraId", INSTANCE);
                return new ChequeResponse(errores);
            }

            List<Cheque> cheques = chequeRepository.findByChequeraIdChequera(idChequera);
            return new ChequeResponse(cheques);
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al listar cheques"), "listarPorChequeraId", INSTANCE, e);
            return new ChequeResponse(errores);
        }
    }

    public ChequeResponse guardar(Cheque cheque) {
        ErrorList errores = new ErrorList();
        try {
            if (cheque.getChequera() == null || cheque.getChequera().getIdChequera() == null) {
                errores.addError(new ErrorEntity("Debe indicar una chequera para asignar el cheque"), "guardar", INSTANCE);
                return new ChequeResponse(errores);
            }

            Optional<Chequera> chequera = chequeraRepository.findById(cheque.getChequera().getIdChequera());
            if (chequera.isEmpty()) {
                errores.addError(new ErrorEntity("Chequera no encontrada"), "guardar", INSTANCE);
                return new ChequeResponse(errores);
            }

            cheque.setChequera(chequera.get());
            Cheque nuevo = chequeRepository.save(cheque);
            return new ChequeResponse(List.of(nuevo));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al guardar cheque"), "guardar", INSTANCE, e);
            return new ChequeResponse(errores);
        }
    }

    public ChequeResponse actualizar(Cheque cheque) {
        ErrorList errores = new ErrorList();
        try {
            if (!chequeRepository.existsById(cheque.getIdCheque())) {
                errores.addError(new ErrorEntity("Cheque no encontrado para actualizar"), "actualizar", INSTANCE);
                return new ChequeResponse(errores);
            }

            Cheque actualizado = chequeRepository.save(cheque);
            return new ChequeResponse(List.of(actualizado));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al actualizar cheque"), "actualizar", INSTANCE, e);
            return new ChequeResponse(errores);
        }
    }

    public ChequeResponse eliminar(Integer idCheque) {
        ErrorList errores = new ErrorList();
        try {
            if (!chequeRepository.existsById(idCheque)) {
                errores.addError(new ErrorEntity("Cheque no encontrado para eliminar"), "eliminar", INSTANCE);
                return new ChequeResponse(errores);
            }

            chequeRepository.deleteById(idCheque);
            return new ChequeResponse(List.of());
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al eliminar cheque"), "eliminar", INSTANCE, e);
            return new ChequeResponse(errores);
        }
    }
}
