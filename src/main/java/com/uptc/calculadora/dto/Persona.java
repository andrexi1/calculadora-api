package com.uptc.calculadora.dto;

/**
 * Representa una fila del archivo CSV compartido vía NFS.
 */
public class Persona {

    private String nombre;
    private String apellido;
    private String ciudad;
    private String edad;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String ciudad, String edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.edad = edad;
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
}
