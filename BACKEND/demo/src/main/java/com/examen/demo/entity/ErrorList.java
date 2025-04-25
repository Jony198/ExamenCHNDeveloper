package com.examen.demo.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class ErrorList extends LinkedList<ErrorEntity> {
    public static int noError = 0;
    public boolean add(ErrorEntity genericError) {
        log.error(genericError.getMensaje());
        noError++;
        return super.add(genericError);
    }

    public boolean addError(ErrorEntity genericError, String methodName,String instance) {
        log.error(noError+">(*)Error ("+methodName+ "->"+instance+"): " + genericError.getMensaje());
        noError++;
        return super.add(genericError);
    }

    public boolean addCatchError(ErrorEntity genericError, String methodName, String instance, Exception e) {
        e.printStackTrace();
        log.error(genericError.getMensaje());
        log.error(noError+">(*)Error ("+methodName+ "->" +instance+")Catch: " + e);
        noError++;
        return super.add(genericError);
    }

    public boolean addErrorList(ErrorList errors, String methodName,String instance){
        for(ErrorEntity error : errors){
            this.addError(error, methodName, instance);
        }
        return true;
    }

}
