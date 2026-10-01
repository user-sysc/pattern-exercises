package domain.clase;

/**
 * Creador concreto: crea un Mago.
 */
public class CreadorMago extends CreadorClasePersonaje {

    @Override
    public ClasePersonaje crearClase() {
        return new Mago();
    }
}
