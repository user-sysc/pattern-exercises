package service;

import domain.Personaje;
import domain.builder.DirectorPersonaje;
import domain.builder.PersonajeBuilder;
import domain.clase.ClasePersonaje;
import domain.clase.ClasePersonajeProvider;
import domain.equipo.FabricaEquipamiento;
import domain.equipo.FabricaEquipamientoProvider;

/**
 * Orquesta los TRES patrones para crear un personaje:
 *
 *  1. Factory Method    -> decide la CLASE del personaje segun el tipo.
 *  2. Abstract Factory  -> decide la FAMILIA de equipamiento segun el mundo.
 *  3. Builder           -> ENSAMBLA el personaje final paso a paso.
 *
 * El service es el unico punto que conoce a los tres; el resto del sistema
 * solo ve las abstracciones.
 */
public class CreacionPersonajeService {

    private final ClasePersonajeProvider claseProvider = new ClasePersonajeProvider();
    private final FabricaEquipamientoProvider equipoProvider = new FabricaEquipamientoProvider();
    private final DirectorPersonaje director = new DirectorPersonaje();

    public Personaje crear(String tipo, String mundo, String nombre, int nivel) {
        ClasePersonaje clase = claseProvider.get(tipo).getClase();
        FabricaEquipamiento fabrica = equipoProvider.get(mundo);
        return director.ensamblar(new PersonajeBuilder(), nombre, nivel, clase, fabrica);
    }
}
