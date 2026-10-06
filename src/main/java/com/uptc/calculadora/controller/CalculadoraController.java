package com.uptc.calculadora.controller;

import com.uptc.calculadora.dto.OperacionRequest;
import com.uptc.calculadora.dto.OperacionResponse;
import com.uptc.calculadora.service.CalculadoraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capa de presentación (controlador). Su única responsabilidad es:
 *   1) recibir la petición HTTP y sus parámetros,
 *   2) armar el objeto de entrada (OperacionRequest),
 *   3) delegar el cálculo a la capa de servicio,
 *   4) devolver la respuesta ya armada.
 *
 * No contiene ninguna lógica matemática ni de validación de reglas de
 * negocio — eso vive en el service. Si algo falla, ni siquiera se hace
 * try/catch aquí: la excepción sube y la captura el GlobalExceptionHandler.
 */
@RestController
public class CalculadoraController {

    private static final Logger log = LoggerFactory.getLogger(CalculadoraController.class);

    private final CalculadoraService calculadoraService;

    // Inyección de dependencias por constructor: Spring provee
    // automáticamente la implementación de CalculadoraService.
    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @GetMapping("/api/calculadora")
    public OperacionResponse calcular(
            @RequestParam double numero1,
            @RequestParam double numero2,
            @RequestParam String operacion) {

        String hostname = System.getenv("HOSTNAME");
        if (hostname == null) {
            hostname = "desconocido";
        }

        OperacionRequest request = new OperacionRequest(numero1, numero2, operacion);
        double resultado = calculadoraService.calcular(request);

        log.info("Operación '{}' ({} , {}) = {} -- atendida por {}",
                operacion, numero1, numero2, resultado, hostname);

        return new OperacionResponse(numero1, numero2, operacion, resultado, hostname);
    }

}
