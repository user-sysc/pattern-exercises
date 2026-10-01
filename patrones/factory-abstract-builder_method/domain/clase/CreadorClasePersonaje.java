package domain.clase;

/**
 * FACTORY METHOD - Creador abstracto.
 *
 * Declara crearClase() y expone getClase() como template: el cliente pide la
 * clase sin acoplarse a las implementaciones concretas.
 */
public abstract class CreadorClasePersonaje {

    public ClasePersonaje getClase() {
        return crearClase();
    }

    public abstract ClasePersonaje crearClase();
}
