package com.examen.demo.service;

import com.examen.demo.entity.ErrorEntity;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Cliente;
import com.examen.demo.repository.ClienteRepository;
import com.examen.demo.response.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private static final String INSTANCE = "ClienteService";

    public ClienteResponse listarTodos() {
        ErrorList errores = new ErrorList();
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            return new ClienteResponse(clientes);
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("No se pudo listar clientes"), "listarTodos", INSTANCE, e);
            return new ClienteResponse(errores);
        }
    }

    public ClienteResponse obtenerPorId(Integer idCliente) {
        ErrorList errores = new ErrorList();
        try {
            Optional<Cliente> cliente = clienteRepository.findById(idCliente);
            if (cliente.isPresent()) {
                return new ClienteResponse(List.of(cliente.get()));
            } else {
                errores.addError(new ErrorEntity("Cliente no encontrado con ID: " + idCliente), "obtenerPorId", INSTANCE);
                return new ClienteResponse(errores);
            }
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al obtener cliente por ID"), "obtenerPorId", INSTANCE, e);
            return new ClienteResponse(errores);
        }
    }

    public ClienteResponse guardar(Cliente cliente) {
        ErrorList errores = new ErrorList();
        try {
            if (clienteRepository.existsByNumeroIdentificacion(cliente.getNumeroIdentificacion())) {
                errores.addError(new ErrorEntity("Ya existe un cliente con este número de identificación"), "guardar", INSTANCE);
                return new ClienteResponse(errores);
            }
            Cliente nuevo = clienteRepository.save(cliente);
            return new ClienteResponse(List.of(nuevo));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al guardar cliente"), "guardar", INSTANCE, e);
            return new ClienteResponse(errores);
        }
    }

    public ClienteResponse actualizar(Cliente cliente) {
        ErrorList errores = new ErrorList();
        try {
            if (!clienteRepository.existsById(cliente.getIdCliente())) {
                errores.addError(new ErrorEntity("No existe el cliente a actualizar"), "actualizar", INSTANCE);
                return new ClienteResponse(errores);
            }
            Cliente actualizado = clienteRepository.save(cliente);
            return new ClienteResponse(List.of(actualizado));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al actualizar cliente"), "actualizar", INSTANCE, e);
            return new ClienteResponse(errores);
        }
    }

    public ClienteResponse eliminar(Integer idCliente) {
        ErrorList errores = new ErrorList();
        try {
            if (!clienteRepository.existsById(idCliente)) {
                errores.addError(new ErrorEntity("Cliente no encontrado para eliminar"), "eliminar", INSTANCE);
                return new ClienteResponse(errores);
            }
            clienteRepository.deleteById(idCliente);
            return new ClienteResponse(List.of()); // Lista vacía como señal de éxito
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al eliminar cliente"), "eliminar", INSTANCE, e);
            return new ClienteResponse(errores);
        }
    }
}
