package com.uptc.calculadora.controller;

import com.uptc.calculadora.dto.ActualizarPersonaDto;
import com.uptc.calculadora.dto.PersonasPageDto;
import com.uptc.calculadora.exception.ArchivoNoDisponibleException;
import com.uptc.calculadora.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    private String getContainerName() {
        String name = System.getenv("CONTAINER_NAME");
        if (name != null && !name.isEmpty()) {
            return name;
        }
        return System.getenv().getOrDefault("HOSTNAME", "desconocido");
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() throws ArchivoNoDisponibleException {
        try {
            long total = personaService.contar();
            Map<String, Object> response = new HashMap<>();
            response.put("total", total);
            response.put("contenedor", getContainerName());
            response.put("maquina", "10.35.115.70");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ArchivoNoDisponibleException("Archivo de personas no disponible");
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) throws ArchivoNoDisponibleException {
        try {
            PersonasPageDto resultado = personaService.listarPersonasPaginado(page, pageSize);

            Map<String, Object> response = new HashMap<>();
            response.put("contenido", resultado.getContenido());
            response.put("pagina", resultado.getPagina());
            response.put("tamanoPagina", resultado.getTamanoPagina());
            response.put("totalRegistros", resultado.getTotalRegistros());
            response.put("totalPaginas", resultado.getTotalPaginas());
            response.put("contenedor", getContainerName());
            response.put("maquina", "10.35.115.70");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ArchivoNoDisponibleException("Archivo de personas no disponible");
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarPersona(@RequestBody ActualizarPersonaDto datos) {
        try {
            if (datos.getNombre() == null || datos.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre no puede estar vacío");
            }
            if (datos.getApellido() == null || datos.getApellido().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El apellido no puede estar vacío");
            }
            if (datos.getCiudad() == null || datos.getCiudad().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La ciudad no puede estar vacía");
            }
            if (datos.getEdad() == null || datos.getEdad().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La edad no puede estar vacía");
            }

            personaService.actualizarPersona(datos.getNumeroLinea(), datos);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Registro en línea " + datos.getNumeroLinea() + " actualizado exitosamente");
            response.put("contenedor", getContainerName());
            response.put("maquina", "10.35.115.70");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Error al actualizar: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
