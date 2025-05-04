package com.examen.demo.service;

import com.examen.demo.entity.ErrorEntity;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Prestamo;
import com.examen.demo.repository.PrestamoRepository;
import com.examen.demo.response.PrestamoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    private static final String INSTANCE = "PrestamoService";

    public PrestamoResponse listarTodos() {
        ErrorList errores = new ErrorList();
        try {
            List<Prestamo> prestamos = prestamoRepository.findAll();
            return new PrestamoResponse(prestamos);
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("No se pudo listar préstamos"), "listarTodos", INSTANCE, e);
            return new PrestamoResponse(errores);
        }
    }

    public PrestamoResponse obtenerPorId(Integer idPrestamo) {
        ErrorList errores = new ErrorList();
        try {
            Optional<Prestamo> prestamo = prestamoRepository.findById(idPrestamo);
            if (prestamo.isPresent()) {
                return new PrestamoResponse(List.of(prestamo.get()));
            } else {
                errores.addError(new ErrorEntity("Préstamo no encontrado con ID: " + idPrestamo), "obtenerPorId", INSTANCE);
                return new PrestamoResponse(errores);
            }
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al obtener préstamo por ID"), "obtenerPorId", INSTANCE, e);
            return new PrestamoResponse(errores);
        }
    }

    public PrestamoResponse guardar(Prestamo prestamo) {
        ErrorList errores = new ErrorList();
        try {
            Prestamo nuevo = prestamoRepository.save(prestamo);
            return new PrestamoResponse(List.of(nuevo));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al guardar préstamo"), "guardar", INSTANCE, e);
            return new PrestamoResponse(errores);
        }
    }

    public PrestamoResponse actualizar(Prestamo prestamo) {
        ErrorList errores = new ErrorList();
        try {
            if (!prestamoRepository.existsById(prestamo.getIdPrestamo())) {
                errores.addError(new ErrorEntity("No existe el préstamo a actualizar"), "actualizar", INSTANCE);
                return new PrestamoResponse(errores);
            }
            Prestamo actualizado = prestamoRepository.save(prestamo);
            return new PrestamoResponse(List.of(actualizado));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al actualizar préstamo"), "actualizar", INSTANCE, e);
            return new PrestamoResponse(errores);
        }
    }

    public PrestamoResponse eliminar(Integer idPrestamo) {
        ErrorList errores = new ErrorList();
        try {
            if (!prestamoRepository.existsById(idPrestamo)) {
                errores.addError(new ErrorEntity("Préstamo no encontrado para eliminar"), "eliminar", INSTANCE);
                return new PrestamoResponse(errores);
            }
            prestamoRepository.deleteById(idPrestamo);
            return new PrestamoResponse(List.of());
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al eliminar préstamo"), "eliminar", INSTANCE, e);
            return new PrestamoResponse(errores);
        }
    }
}
