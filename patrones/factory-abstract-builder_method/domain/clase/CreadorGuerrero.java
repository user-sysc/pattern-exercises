package domain.clase;

/**
 * Creador concreto: crea un Guerrero.
 */
public class CreadorGuerrero extends CreadorClasePersonaje {

    @Override
    public ClasePersonaje crearClase() {
        return new Guerrero();
    }
}
