package victoriana;

import muebles.Sofa;

/**
 * Producto concreto de la familia Victoriana.
 */
public class SofaVictoriano implements Sofa {

    @Override
    public String recostarse() {
        return "Te recuestas en un sofa de estilo victoriano.";
    }
}
