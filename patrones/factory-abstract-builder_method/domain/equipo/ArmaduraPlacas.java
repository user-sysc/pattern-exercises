package domain.equipo;

/**
 * Producto concreto de la familia MEDIEVAL.
 */
public class ArmaduraPlacas implements Armadura {

    @Override
    public String nombre() {
        return "Armadura de placas";
    }

    @Override
    public int defensa() {
        return 12;
    }
}
