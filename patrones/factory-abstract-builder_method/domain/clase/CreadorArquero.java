package domain.clase;

/**
 * Creador concreto: crea un Arquero.
 */
public class CreadorArquero extends CreadorClasePersonaje {

    @Override
    public ClasePersonaje crearClase() {
        return new Arquero();
    }
}
