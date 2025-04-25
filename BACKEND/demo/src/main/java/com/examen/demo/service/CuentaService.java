package com.examen.demo.service;

import com.examen.demo.entity.ErrorEntity;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Cliente;
import com.examen.demo.model.Cuenta;
import com.examen.demo.repository.ClienteRepository;
import com.examen.demo.repository.CuentaRepository;
import com.examen.demo.response.CuentaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private static final String INSTANCE = "CuentaService";

    public CuentaResponse listarPorClienteId(Integer idCliente) {
        ErrorList errores = new ErrorList();
        try {
            if (!clienteRepository.existsById(idCliente)) {
                errores.addError(new ErrorEntity("Cliente no encontrado"), "listarPorClienteId", INSTANCE);
                return new CuentaResponse(errores);
            }

            List<Cuenta> cuentas = cuentaRepository.findByClienteIdCliente(idCliente);
            return new CuentaResponse(cuentas);
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al listar cuentas del cliente"), "listarPorClienteId", INSTANCE, e);
            return new CuentaResponse(errores);
        }
    }

    public CuentaResponse guardar(Cuenta cuenta) {
        ErrorList errores = new ErrorList();
        try {
            if (cuenta.getCliente() == null || cuenta.getCliente().getIdCliente() == null) {
                errores.addError(new ErrorEntity("Se requiere el ID del cliente para crear la cuenta"), "guardar", INSTANCE);
                return new CuentaResponse(errores);
            }

            Optional<Cliente> cliente = clienteRepository.findById(cuenta.getCliente().getIdCliente());
            if (cliente.isEmpty()) {
                errores.addError(new ErrorEntity("Cliente no encontrado"), "guardar", INSTANCE);
                return new CuentaResponse(errores);
            }

            cuenta.setCliente(cliente.get());
            Cuenta nueva = cuentaRepository.save(cuenta);
            return new CuentaResponse(List.of(nueva));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al guardar la cuenta"), "guardar", INSTANCE, e);
            return new CuentaResponse(errores);
        }
    }

    public CuentaResponse actualizar(Cuenta cuenta) {
        ErrorList errores = new ErrorList();
        try {
            if (!cuentaRepository.existsById(cuenta.getIdCuenta())) {
                errores.addError(new ErrorEntity("Cuenta no encontrada para actualizar"), "actualizar", INSTANCE);
                return new CuentaResponse(errores);
            }
            Cuenta actualizada = cuentaRepository.save(cuenta);
            return new CuentaResponse(List.of(actualizada));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al actualizar la cuenta"), "actualizar", INSTANCE, e);
            return new CuentaResponse(errores);
        }
    }

    public CuentaResponse eliminar(Integer idCuenta) {
        ErrorList errores = new ErrorList();
        try {
            if (!cuentaRepository.existsById(idCuenta)) {
                errores.addError(new ErrorEntity("Cuenta no encontrada para eliminar"), "eliminar", INSTANCE);
                return new CuentaResponse(errores);
            }
            cuentaRepository.deleteById(idCuenta);
            return new CuentaResponse(List.of());
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al eliminar la cuenta"), "eliminar", INSTANCE, e);
            return new CuentaResponse(errores);
        }
    }
}
