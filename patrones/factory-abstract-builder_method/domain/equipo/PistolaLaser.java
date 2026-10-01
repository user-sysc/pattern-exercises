package domain.equipo;

/**
 * Producto concreto de la familia FUTURISTA.
 */
public class PistolaLaser implements Arma {

    @Override
    public String nombre() {
        return "Pistola laser";
    }

    @Override
    public int danio() {
        return 20;
    }
}
