package com.examen.demo.service;

import com.examen.demo.entity.ErrorEntity;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Prestamo;
import com.examen.demo.model.SolicitudPrestamo;
import com.examen.demo.repository.PrestamoRepository;
import com.examen.demo.repository.SolicitudPrestamoRepository;
import com.examen.demo.response.SolicitudPrestamoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudPrestamoService {

    @Autowired
    private SolicitudPrestamoRepository solicitudRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    private static final String INSTANCE = "SolicitudPrestamoService";

    public SolicitudPrestamoResponse listarTodos() {
        ErrorList errores = new ErrorList();
        try {
            List<SolicitudPrestamo> solicitudes = solicitudRepository.findAll();
            return new SolicitudPrestamoResponse(solicitudes);
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("No se pudo listar solicitudes"), "listarTodos", INSTANCE, e);
            return new SolicitudPrestamoResponse(errores);
        }
    }

    public SolicitudPrestamoResponse obtenerPorId(Integer idSolicitud) {
        ErrorList errores = new ErrorList();
        try {
            Optional<SolicitudPrestamo> solicitud = solicitudRepository.findById(idSolicitud);
            if (solicitud.isPresent()) {
                return new SolicitudPrestamoResponse(List.of(solicitud.get()));
            } else {
                errores.addError(new ErrorEntity("Solicitud no encontrada con ID: " + idSolicitud), "obtenerPorId", INSTANCE);
                return new SolicitudPrestamoResponse(errores);
            }
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al obtener solicitud por ID"), "obtenerPorId", INSTANCE, e);
            return new SolicitudPrestamoResponse(errores);
        }
    }

    public SolicitudPrestamoResponse guardar(SolicitudPrestamo solicitud) {
        ErrorList errores = new ErrorList();
        try {
            solicitud.setFechaSolicitud(LocalDateTime.now());
            SolicitudPrestamo nueva = solicitudRepository.save(solicitud);
            return new SolicitudPrestamoResponse(List.of(nueva));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al guardar solicitud"), "guardar", INSTANCE, e);
            return new SolicitudPrestamoResponse(errores);
        }
    }

    public SolicitudPrestamoResponse actualizarEstado(SolicitudPrestamo solicitudInput) {
        ErrorList errores = new ErrorList();
        try {
            Optional<SolicitudPrestamo> optional = solicitudRepository.findById(solicitudInput.getIdSolicitud());
            if (!optional.isPresent()) {
                errores.addError(new ErrorEntity("Solicitud no encontrada con ID: " + solicitudInput.getIdSolicitud()), "actualizarEstado", INSTANCE);
                return new SolicitudPrestamoResponse(errores);
            }

            SolicitudPrestamo solicitud = optional.get();

            if (!"PENDIENTE".equals(solicitud.getEstado())) {
                errores.addError(new ErrorEntity("Solo se pueden actualizar solicitudes en estado PENDIENTE."), "actualizarEstado", INSTANCE);
                return new SolicitudPrestamoResponse(errores);
            }

            if (!"APROBADO".equals(solicitudInput.getEstado()) && !"RECHAZADO".equals(solicitudInput.getEstado())) {
                errores.addError(new ErrorEntity("Estado inválido. Debe ser 'APROBADO' o 'RECHAZADO'."), "actualizarEstado", INSTANCE);
                return new SolicitudPrestamoResponse(errores);
            }

            solicitud.setEstado(solicitudInput.getEstado());

            if ("RECHAZADO".equals(solicitudInput.getEstado())) {
                if (solicitudInput.getMotivoRechazo() == null || solicitudInput.getMotivoRechazo().trim().isEmpty()) {
                    errores.addError(new ErrorEntity("Debe proporcionar un motivo de rechazo."), "actualizarEstado", INSTANCE);
                    return new SolicitudPrestamoResponse(errores);
                }
                solicitud.setMotivoRechazo(solicitudInput.getMotivoRechazo().trim());
            } else {
                solicitud.setMotivoRechazo(null);
            }

            solicitudRepository.save(solicitud);
            if("APROBADO".equals(solicitudInput.getEstado())){
                Prestamo prestamo = new Prestamo();
                prestamo.setSolicitud(solicitud);
                prestamo.setEstado("VIGENTE");
                prestamo.setFechaAprobacion(LocalDateTime.now());
                prestamo.setMontoAprobado(solicitud.getMonto());
                prestamo.setSaldoPendiente(solicitud.getMonto());
                prestamoRepository.save(prestamo);
            }
            return new SolicitudPrestamoResponse(List.of(solicitud));
        } catch (Exception e) {
            errores.addCatchError(new ErrorEntity("Error al actualizar estado de solicitud"), "actualizarEstado", INSTANCE, e);
            return new SolicitudPrestamoResponse(errores);
        }
    }
}
