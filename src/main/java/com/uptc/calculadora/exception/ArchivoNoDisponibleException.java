package com.uptc.calculadora.exception;

/**
 * Se lanza cuando no fue posible leer el archivo CSV de personas desde
 * la ruta local (montada vía NFS desde la máquina virtual dedicada),
 * ya sea porque el punto de montaje no está activo, el archivo no
 * existe, o hay un error de lectura.
 */
public class ArchivoNoDisponibleException extends RuntimeException {

    public ArchivoNoDisponibleException(String detalle) {
        super("No fue posible obtener el archivo remoto: " + detalle);
    }
}
