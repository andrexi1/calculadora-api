package com.uptc.calculadora.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Captura, en un único lugar, todas las excepciones que puedan lanzarse
 * en cualquier controlador de la aplicación, y las traduce a una respuesta
 * HTTP con el código y el mensaje adecuados.
 *
 * Esto evita tener bloques try/catch repetidos dentro de cada método del
 * controlador, y evita que Spring devuelva su página de error genérica
 * (que expondría detalles internos como el stack trace).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private String hostnameActual() {
        String hostname = System.getenv("HOSTNAME");
        return (hostname != null) ? hostname : "desconocido";
    }

    // Caso: operación no reconocida (suma/resta/multiplicacion/division)
    @ExceptionHandler(OperacionNoSoportadaException.class)
    public ResponseEntity<ErrorResponse> manejarOperacionNoSoportada(OperacionNoSoportadaException ex) {
        log.warn("Operación no soportada solicitada: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Operación no soportada",
                ex.getMessage(),
                hostnameActual()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Caso: división por cero
    @ExceptionHandler(DivisionPorCeroException.class)
    public ResponseEntity<ErrorResponse> manejarDivisionPorCero(DivisionPorCeroException ex) {
        log.warn("Intento de división por cero");
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error aritmético",
                ex.getMessage(),
                hostnameActual()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Caso: el cliente envía un texto donde se esperaba un número
    // (ej. numero1=abc en vez de numero1=10)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> manejarTipoInvalido(MethodArgumentTypeMismatchException ex) {
        log.warn("Parámetro con tipo inválido: {}", ex.getName());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Parámetro inválido",
                "El parámetro '" + ex.getName() + "' debe ser un número válido.",
                hostnameActual()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Caso: el archivo remoto (VM de archivos) no está disponible
    @ExceptionHandler(ArchivoNoDisponibleException.class)
    public ResponseEntity<ErrorResponse> manejarArchivoNoDisponible(ArchivoNoDisponibleException ex) {
        log.warn("Archivo remoto no disponible: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Archivo no disponible",
                ex.getMessage(),
                hostnameActual()
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

   
    // Cualquier otro error no anticipado (red de seguridad)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarErrorGeneral(Exception ex) {
        log.error("Error inesperado: ", ex);
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno",
                "Ocurrió un error inesperado al procesar la solicitud.",
                hostnameActual()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
