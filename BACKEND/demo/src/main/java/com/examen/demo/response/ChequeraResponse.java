package com.examen.demo.response;

import com.examen.demo.entity.AbstractResponse;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Chequera;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChequeraResponse extends AbstractResponse {
    private List<Chequera> chequeras;

    public ChequeraResponse(ErrorList errores) {
        super(errores);
    }

    public ChequeraResponse(List<Chequera> chequeras) {
        this.chequeras = chequeras;
        this.successful = true;
    }
}
