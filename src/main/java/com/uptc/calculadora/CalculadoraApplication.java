package com.uptc.calculadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación.
 * Al ejecutarse, Spring Boot levanta un servidor web embebido (Tomcat)
 * y escanea automáticamente los paquetes controller, service y exception
 * en busca de las anotaciones @RestController, @Service, etc.
 */
@SpringBootApplication
public class CalculadoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculadoraApplication.class, args);
    }

}
