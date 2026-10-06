package com.uptc.calculadora.exception;

import java.time.LocalDateTime;

/**
 * Estructura uniforme para devolver cualquier error al cliente,
 * en vez de dejar que Spring devuelva su página de error genérica por defecto.
 */
public class ErrorResponse {

    private LocalDateTime fecha;
    private int status;
    private String error;
    private String mensaje;
    private String atendidoPor;

    public ErrorResponse(int status, String error, String mensaje, String atendidoPor) {
        this.fecha = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.mensaje = mensaje;
        this.atendidoPor = atendidoPor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAtendidoPor() {
        return atendidoPor;
    }
}
