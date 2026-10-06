package com.uptc.calculadora.dto;

/**
 * Representa la respuesta que el servicio devuelve al cliente cuando la
 * operación se ejecuta correctamente.
 *
 * Se incluye el campo "atendidoPor" con el hostname del contenedor que
 * procesó la petición. Docker asigna automáticamente esta variable de
 * entorno (HOSTNAME) igual al valor que se le indique con --hostname al
 * momento de levantar el contenedor, lo cual permite identificar, sin
 * herramientas externas, qué instancia específica respondió cada petición
 * cuando se tienen múltiples réplicas detrás de un balanceador de carga.
 */
public class OperacionResponse {

    private double numero1;
    private double numero2;
    private String operacion;
    private double resultado;
    private String atendidoPor;

    public OperacionResponse(double numero1, double numero2, String operacion,
                              double resultado, String atendidoPor) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.operacion = operacion;
        this.resultado = resultado;
        this.atendidoPor = atendidoPor;
    }

    public double getNumero1() {
        return numero1;
    }

    public double getNumero2() {
        return numero2;
    }

    public String getOperacion() {
        return operacion;
    }

    public double getResultado() {
        return resultado;
    }

    public String getAtendidoPor() {
        return atendidoPor;
    }
}
