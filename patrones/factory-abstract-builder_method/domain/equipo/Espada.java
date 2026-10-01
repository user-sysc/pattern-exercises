package domain.equipo;

/**
 * Producto concreto de la familia MEDIEVAL.
 */
public class Espada implements Arma {

    @Override
    public String nombre() {
        return "Espada medieval";
    }

    @Override
    public int danio() {
        return 15;
    }
}
