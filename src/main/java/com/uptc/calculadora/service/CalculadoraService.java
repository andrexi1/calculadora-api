package com.uptc.calculadora.service;

import com.uptc.calculadora.dto.OperacionRequest;

/**
 * Contrato de la capa de servicio: define QUÉ hace el servicio,
 * sin exponer CÓMO lo hace. El controlador depende de esta interfaz,
 * no de la implementación concreta — esto permite, por ejemplo,
 * cambiar la implementación o crear una versión de prueba (mock)
 * sin tocar el controlador.
 */
public interface CalculadoraService {

    double calcular(OperacionRequest request);

}
