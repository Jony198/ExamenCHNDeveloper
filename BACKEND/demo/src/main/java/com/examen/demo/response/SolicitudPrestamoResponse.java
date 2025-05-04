package com.examen.demo.response;

import com.examen.demo.entity.AbstractResponse;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.SolicitudPrestamo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SolicitudPrestamoResponse extends AbstractResponse {
    List<SolicitudPrestamo> solicitudes;

    public SolicitudPrestamoResponse(ErrorList errores) {
        super(errores);
    }

    public SolicitudPrestamoResponse(List<SolicitudPrestamo> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
