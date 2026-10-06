package com.uptc.calculadora.dto;

public class InfoDto {
    private String contenedor;
    private String maquina;
    private String puerto;

    public InfoDto() {}

    public InfoDto(String contenedor, String maquina, String puerto) {
        this.contenedor = contenedor;
        this.maquina = maquina;
        this.puerto = puerto;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
}
