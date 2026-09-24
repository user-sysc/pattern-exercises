package victoriana;

import muebles.Silla;

/**
 * Producto concreto de la familia Victoriana.
 */
public class SillaVictoriana implements Silla {

    @Override
    public String sentarse() {
        return "Te sientas en una silla de estilo victoriano.";
    }
}
