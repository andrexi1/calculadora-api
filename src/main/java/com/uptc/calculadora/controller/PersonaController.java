package com.uptc.calculadora.controller;

import com.uptc.calculadora.dto.ActualizarPersonaDto;
import com.uptc.calculadora.dto.PersonasPageDto;
import com.uptc.calculadora.dto.Persona;
import com.uptc.calculadora.exception.ArchivoNoDisponibleException;
import com.uptc.calculadora.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    private Map<String, Object> addMetadata(Object data) {
	   // AUTO-DEPLOY TEST - Cambio en vivo
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("data", data);
        response.put("desarrollador", "Gustavo Andres Barrera Arcos");
        response.put("contenedor", System.getenv().getOrDefault("HOSTNAME", "desconocido"));
        response.put("maquina", "10.172.14.70");
        response.put("version", "v2");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

@GetMapping("/count")
public ResponseEntity<?> count() throws ArchivoNoDisponibleException {
    try {
        long total = personaService.contar();
        Map<String, Object> countData = new HashMap<>();
        countData.put("total", total);
        return ResponseEntity.ok(addMetadata(countData));
    } catch (Exception e) {
        e.printStackTrace();
        throw new ArchivoNoDisponibleException("Error al consultar personas: " + e.getMessage());
    }
}

    @GetMapping("/paginado")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) throws ArchivoNoDisponibleException {
        try {
            PersonasPageDto resultado = personaService.listarPersonasPaginado(page, pageSize);
            return ResponseEntity.ok(addMetadata(resultado));
        } catch (Exception e) {
            throw new ArchivoNoDisponibleException("Archivo de personas no disponible");
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarPersona(@RequestBody ActualizarPersonaDto datos) {
        try {
            if (datos.getNombre() == null || datos.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(addMetadata("El nombre no puede estar vacío"));
            }
            if (datos.getApellido() == null || datos.getApellido().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(addMetadata("El apellido no puede estar vacío"));
            }
            if (datos.getCiudad() == null || datos.getCiudad().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(addMetadata("La ciudad no puede estar vacía"));
            }
            if (datos.getEdad() == null || datos.getEdad().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(addMetadata("La edad no puede estar vacía"));
            }

            personaService.actualizarPersona(datos.getNumeroLinea(), datos);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Registro en línea " + datos.getNumeroLinea() + " actualizado exitosamente");
            
            return ResponseEntity.ok(addMetadata(response));
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(addMetadata(response));
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Error al actualizar: " + e.getMessage());
            return ResponseEntity.internalServerError().body(addMetadata(response));
        }
    }
}