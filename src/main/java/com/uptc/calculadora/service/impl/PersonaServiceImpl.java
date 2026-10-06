package com.uptc.calculadora.service.impl;

import com.uptc.calculadora.dto.ActualizarPersonaDto;
import com.uptc.calculadora.dto.Persona;
import com.uptc.calculadora.dto.PersonasPageDto;
import com.uptc.calculadora.model.PersonaEntity;
import com.uptc.calculadora.repository.PersonaRepository;
import com.uptc.calculadora.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public PersonasPageDto listarPersonasPaginado(int page, int pageSize) {
        Page<PersonaEntity> pageResult = personaRepository.findAll(
                PageRequest.of(page, pageSize, Sort.by("id").ascending())
        );

        List<Persona> personas = pageResult.getContent().stream()
                .map(e -> new Persona(
                        e.getNombre(),
                        e.getApellido(),
                        e.getCiudad(),
                        String.valueOf(e.getEdad())
                ))
                .collect(Collectors.toList());

        return new PersonasPageDto(
                personas,
                page,
                pageSize,
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }

    @Override
    public long contar() {
        return personaRepository.count();
    }

    @Override
    @Transactional
    public void actualizarPersona(int numeroLinea, ActualizarPersonaDto datos) throws Exception {
        Long id = (long) numeroLinea;

        PersonaEntity entity = personaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el registro con id " + id));

        entity.setNombre(datos.getNombre().trim());
        entity.setApellido(datos.getApellido().trim());
        entity.setCiudad(datos.getCiudad().trim());
        entity.setEdad(Integer.parseInt(datos.getEdad().trim()));

        personaRepository.save(entity);
    }
}
