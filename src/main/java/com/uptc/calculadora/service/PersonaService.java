package com.uptc.calculadora.service;

import com.uptc.calculadora.dto.ActualizarPersonaDto;
import com.uptc.calculadora.dto.PersonasPageDto;

public interface PersonaService {
    PersonasPageDto listarPersonasPaginado(int page, int pageSize);
    long contar();
    void actualizarPersona(int numeroLinea, ActualizarPersonaDto datos) throws Exception;
}
