package com.uptc.calculadora.exception;

/**
 * Se lanza cuando se solicita una división y el segundo número (divisor) es cero,
 * caso matemáticamente indefinido que debe manejarse explícitamente.
 */
public class DivisionPorCeroException extends RuntimeException {

    public DivisionPorCeroException() {
        super("No es posible dividir por cero.");
    }
}
