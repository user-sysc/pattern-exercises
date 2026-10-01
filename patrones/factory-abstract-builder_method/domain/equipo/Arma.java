package domain.equipo;

/**
 * ABSTRACT FACTORY - Producto abstracto.
 * Arma que puede crear una familia de equipamiento.
 */
public interface Arma {

    String nombre();

    int danio();
}
