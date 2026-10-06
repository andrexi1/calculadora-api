package com.uptc.calculadora.dto;

/**
 * Representa los datos de entrada que el cliente envía: los dos números
 * y el nombre de la operación a realizar (suma, resta, multiplicacion, division).
 *
 * Mantener esto como una clase separada (en vez de recibir parámetros sueltos
 * directamente en el controlador) permite reutilizarla, validarla, y facilita
 * el testeo de la capa de servicio de forma independiente del controlador.
 */
public class OperacionRequest {

    private double numero1;
    private double numero2;
    private String operacion;

    public OperacionRequest() {
    }

    public OperacionRequest(double numero1, double numero2, String operacion) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.operacion = operacion;
    }

    public double getNumero1() {
        return numero1;
    }

    public void setNumero1(double numero1) {
        this.numero1 = numero1;
    }

    public double getNumero2() {
        return numero2;
    }

    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
}
