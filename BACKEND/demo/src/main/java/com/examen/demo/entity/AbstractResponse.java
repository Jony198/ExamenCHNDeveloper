package com.examen.demo.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class AbstractResponse implements Serializable {
    public Boolean successful;
    public ErrorList errores = new ErrorList();


    public AbstractResponse() {
        this.successful = true;
    }


    public AbstractResponse(ErrorList errors) {
        this.successful = false;
        this.errores = errors;
    }



}
