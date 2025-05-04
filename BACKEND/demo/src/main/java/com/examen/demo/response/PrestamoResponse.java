package com.examen.demo.response;

import com.examen.demo.entity.AbstractResponse;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Prestamo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrestamoResponse extends AbstractResponse {
    private List<Prestamo> prestamos;

    public PrestamoResponse(ErrorList errores) {
        super(errores);
    }

    public PrestamoResponse(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
