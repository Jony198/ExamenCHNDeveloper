package com.examen.demo.response;

import com.examen.demo.entity.AbstractResponse;
import com.examen.demo.entity.ErrorList;
import com.examen.demo.model.Cheque;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChequeResponse extends AbstractResponse {
    private List<Cheque> cheques;

    public ChequeResponse(ErrorList errores) {
        super(errores);
    }

    public ChequeResponse(List<Cheque> cheques) {
        this.cheques = cheques;
        this.successful = true;
    }
}
