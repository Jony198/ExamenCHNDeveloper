package com.examen.demo.response;

import com.examen.demo.entity.AbstractResponse;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Cuenta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CuentaResponse extends AbstractResponse {
    private List<Cuenta> cuentas;

    public CuentaResponse(ErrorList errores) {
        super(errores);
    }

    public CuentaResponse(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
        this.successful = true;
    }
}
