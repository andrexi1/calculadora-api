package com.uptc.calculadora.exception;

/**
 * Se lanza cuando el cliente pide una operación distinta a
 * suma, resta, multiplicacion o division (por ejemplo, un typo
 * o una operación inventada).
 */
public class OperacionNoSoportadaException extends RuntimeException {

    public OperacionNoSoportadaException(String operacion) {
        super("La operación '" + operacion + "' no es soportada. " +
              "Use: suma, resta, multiplicacion o division.");
    }
}
