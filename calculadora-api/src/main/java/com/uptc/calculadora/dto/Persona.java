package com.uptc.calculadora.dto;

/**
 * Representa una fila del archivo CSV compartido vía NFS.
 */
public class Persona {

    private String id;
    private String nombre;
    private String edad;
    private String ciudad;

    public Persona() {
    }

    public Persona(String id, String nombre, String edad, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdad() {
        return edad;
    }

    public String getCiudad() {
        return ciudad;
    }
}
