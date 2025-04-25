package com.examen.demo.service;

import com.examen.demo.entity.ErrorEntity;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Chequera;
import com.examen.demo.model.Cuenta;
import com.examen.demo.repository.ChequeraRepository;
import com.examen.demo.repository.CuentaRepository;
import com.examen.demo.response.ChequeraResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChequeraService {

    @Autowired
    private ChequeraRepository chequeraRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    private static final String INSTANCE = "ChequeraService";

    public ChequeraResponse listarPorCuentaId(Integer idCuenta) {
        ErrorList errores = new ErrorList();
        try {
            if (!cuentaRepository.existsById(idCuenta)) {
                errores.addError(new ErrorEntity("Cuenta no encontrada"), "listarPorCuentaId", INSTANCE);
                return new ChequeraResponse(errores);
            }

            List<Chequera> chequeras = chequeraRepository.findByCuentaIdCuenta(idCuenta);
            return new ChequeraResponse(chequeras);
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al listar chequeras"), "listarPorCuentaId", INSTANCE, e);
            return new ChequeraResponse(errores);
        }
    }

    public ChequeraResponse guardar(Chequera chequera) {
        ErrorList errores = new ErrorList();
        try {
            if (chequera.getCuenta() == null || chequera.getCuenta().getIdCuenta() == null) {
                errores.addError(new ErrorEntity("Debe indicar una cuenta para asignar la chequera"), "guardar", INSTANCE);
                return new ChequeraResponse(errores);
            }

            Optional<Cuenta> cuenta = cuentaRepository.findById(chequera.getCuenta().getIdCuenta());
            if (cuenta.isEmpty()) {
                errores.addError(new ErrorEntity("Cuenta no encontrada"), "guardar", INSTANCE);
                return new ChequeraResponse(errores);
            }

            chequera.setCuenta(cuenta.get());
            Chequera nueva = chequeraRepository.save(chequera);
            return new ChequeraResponse(List.of(nueva));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al guardar chequera"), "guardar", INSTANCE, e);
            return new ChequeraResponse(errores);
        }
    }

    public ChequeraResponse actualizar(Chequera chequera) {
        ErrorList errores = new ErrorList();
        try {
            if (!chequeraRepository.existsById(chequera.getIdChequera())) {
                errores.addError(new ErrorEntity("Chequera no encontrada para actualizar"), "actualizar", INSTANCE);
                return new ChequeraResponse(errores);
            }

            Chequera actualizada = chequeraRepository.save(chequera);
            return new ChequeraResponse(List.of(actualizada));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al actualizar chequera"), "actualizar", INSTANCE, e);
            return new ChequeraResponse(errores);
        }
    }

    public ChequeraResponse eliminar(Integer idChequera) {
        ErrorList errores = new ErrorList();
        try {
            if (!chequeraRepository.existsById(idChequera)) {
                errores.addError(new ErrorEntity("Chequera no encontrada para eliminar"), "eliminar", INSTANCE);
                return new ChequeraResponse(errores);
            }

            chequeraRepository.deleteById(idChequera);
            return new ChequeraResponse(List.of());
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al eliminar chequera"), "eliminar", INSTANCE, e);
            return new ChequeraResponse(errores);
        }
    }
}
