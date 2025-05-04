package com.examen.demo.service;

import com.examen.demo.entity.ErrorEntity;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Pago;
import com.examen.demo.model.Prestamo;
import com.examen.demo.repository.PagoRepository;
import com.examen.demo.repository.PrestamoRepository;
import com.examen.demo.response.PagoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    private static final String INSTANCE = "PagoService";

    public PagoResponse listarTodos() {
        ErrorList errores = new ErrorList();
        try {
            List<Pago> pagos = pagoRepository.findAll();
            return new PagoResponse(pagos);
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("No se pudo listar los pagos"), "listarTodos", INSTANCE, e);
            return new PagoResponse(errores);
        }
    }

    public PagoResponse obtenerPorId(Integer idPago) {
        ErrorList errores = new ErrorList();
        try {
            Optional<Pago> pago = pagoRepository.findById(idPago);
            if (pago.isPresent()) {
                return new PagoResponse(List.of(pago.get()));
            } else {
                errores.addError(new ErrorEntity("Pago no encontrado con ID: " + idPago), "obtenerPorId", INSTANCE);
                return new PagoResponse(errores);
            }
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al obtener pago por ID"), "obtenerPorId", INSTANCE, e);
            return new PagoResponse(errores);
        }
    }

    public PagoResponse guardar(Pago pago) {
        ErrorList errores = new ErrorList();
        try {
            Prestamo prestamo = pago.getPrestamo();

            // Validación: no permitir pagar más de lo que se debe
            if (pago.getMontoPagado().compareTo(prestamo.getSaldoPendiente()) > 0) {
                errores.addError(new ErrorEntity("El monto pagado excede el saldo pendiente"), "guardar", INSTANCE);
                return new PagoResponse(errores);
            }

            // Actualizar saldo pendiente
            prestamo.setSaldoPendiente(prestamo.getSaldoPendiente().subtract(pago.getMontoPagado()));

            // Si se finiquita
            if (prestamo.getSaldoPendiente().compareTo(BigDecimal.ZERO) == 0) {
                prestamo.setEstado("FINIQUITADO");
            }

            prestamoRepository.save(prestamo);
            Pago nuevoPago = pagoRepository.save(pago);
            return new PagoResponse(List.of(nuevoPago));

        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al guardar pago"), "guardar", INSTANCE, e);
            return new PagoResponse(errores);
        }
    }


    public PagoResponse eliminar(Integer idPago) {
        ErrorList errores = new ErrorList();
        try {
            if (!pagoRepository.existsById(idPago)) {
                errores.addError(new ErrorEntity("Pago no encontrado para eliminar"), "eliminar", INSTANCE);
                return new PagoResponse(errores);
            }
            pagoRepository.deleteById(idPago);
            return new PagoResponse(List.of());
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al eliminar el pago"), "eliminar", INSTANCE, e);
            return new PagoResponse(errores);
        }
    }
}
