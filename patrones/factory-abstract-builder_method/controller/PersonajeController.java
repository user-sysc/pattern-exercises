package controller;

import domain.Personaje;
import service.CreacionPersonajeService;

/**
 * Controlador: pide el personaje al service y lo muestra.
 * No conoce fabricas, creadores ni builders.
 */
public class PersonajeController {

    private final CreacionPersonajeService service = new CreacionPersonajeService();

    public void crearYMostrar(String tipo, String mundo, String nombre, int nivel) {
        Personaje personaje = service.crear(tipo, mundo, nombre, nivel);
        System.out.println("--- " + nombre + " | tipo=" + tipo + " | mundo=" + mundo + " ---");
        System.out.println(personaje);
        System.out.println();
    }
}
