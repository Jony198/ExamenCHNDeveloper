package com.examen.demo.response;

import com.examen.demo.entity.AbstractResponse;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Pago;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagoResponse extends AbstractResponse {
    List<Pago> pagos;

    public PagoResponse(ErrorList errores) {
        super(errores);
    }

    public PagoResponse(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
