package com.examen.demo.entity;

public class ErrorEntity {
    private String mensaje;

    public ErrorEntity(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}