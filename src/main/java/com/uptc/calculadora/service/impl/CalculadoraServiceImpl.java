package com.uptc.calculadora.service.impl;

import com.uptc.calculadora.dto.OperacionRequest;
import com.uptc.calculadora.exception.DivisionPorCeroException;
import com.uptc.calculadora.exception.OperacionNoSoportadaException;
import com.uptc.calculadora.service.CalculadoraService;
import org.springframework.stereotype.Service;

/**
 * Implementación real de la lógica de negocio. Al llevar la anotación
 * @Service, Spring la registra automáticamente como el "bean" que se
 * inyecta donde se pida un CalculadoraService.
 *
 * Aquí, y solo aquí, vive la lógica matemática — el controlador no sabe
 * (ni le importa) cómo se calcula una resta o una división, solo delega
 * la petición a esta capa.
 */
@Service
public class CalculadoraServiceImpl implements CalculadoraService {

    @Override
    public double calcular(OperacionRequest request) {
        String operacion = request.getOperacion().trim().toLowerCase();
        double n1 = request.getNumero1();
        double n2 = request.getNumero2();

        switch (operacion) {
            case "suma":
                return n1 + n2;

            case "resta":
                return n1 - n2;

            case "multiplicacion":
                return n1 * n2;

            case "division":
                if (n2 == 0) {
                    throw new DivisionPorCeroException();
                }
                return n1 / n2;

            default:
                throw new OperacionNoSoportadaException(request.getOperacion());
        }
    }
}
