package com.uptc.calculadora.dto;

public class ActualizarPersonaDto {
    private int numeroLinea;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String edad;

    // Constructores
    public ActualizarPersonaDto() {}

    public ActualizarPersonaDto(int numeroLinea, String nombre, String apellido, String ciudad, String edad) {
        this.numeroLinea = numeroLinea;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.edad = edad;
    }

    // Getters
    public int getNumeroLinea() {
        return numeroLinea;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEdad() {
        return edad;
    }

    // Setters
    public void setNumeroLinea(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
