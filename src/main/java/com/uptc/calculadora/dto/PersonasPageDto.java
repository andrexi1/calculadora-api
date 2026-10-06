package com.uptc.calculadora.dto;

import java.util.List;

/**
 * DTO que encapsula una página de personas con metadatos de paginación.
 */
public class PersonasPageDto {

    private List<Persona> contenido;
    private int pagina;
    private int tamanoPagina;
    private long totalRegistros;
    private long totalPaginas;

    public PersonasPageDto() {
    }

    public PersonasPageDto(List<Persona> contenido, int pagina, int tamanoPagina,
                          long totalRegistros, long totalPaginas) {
        this.contenido = contenido;
        this.pagina = pagina;
        this.tamanoPagina = tamanoPagina;
        this.totalRegistros = totalRegistros;
        this.totalPaginas = totalPaginas;
    }

    public List<Persona> getContenido() {
        return contenido;
    }

    public int getPagina() {
        return pagina;
    }

    public int getTamanoPagina() {
        return tamanoPagina;
    }

    public long getTotalRegistros() {
        return totalRegistros;
    }

    public long getTotalPaginas() {
        return totalPaginas;
    }
}
