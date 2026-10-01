package domain.builder;

import domain.Personaje;
import domain.clase.ClasePersonaje;
import domain.equipo.FabricaEquipamiento;

/**
 * BUILDER - Director.
 *
 * Conoce la SECUENCIA estandar de armado de un personaje. Recibe la clase
 * (creada por el Factory Method) y la familia de equipamiento (creada por el
 * Abstract Factory), y ordena los pasos del Builder.
 */
public class DirectorPersonaje {

    public Personaje ensamblar(PersonajeBuilder builder, String nombre, int nivel,
                               ClasePersonaje clase, FabricaEquipamiento fabrica) {
        return builder
                .nombre(nombre)
                .nivel(nivel)
                .clase(clase)
                .arma(fabrica.crearArma())
                .armadura(fabrica.crearArmadura())
                .escudo(fabrica.crearEscudo())
                .build();
    }
}
