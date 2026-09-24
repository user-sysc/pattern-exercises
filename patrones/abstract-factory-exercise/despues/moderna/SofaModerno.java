package moderna;

import muebles.Sofa;

/**
 * Producto concreto de la familia Moderna.
 */
public class SofaModerno implements Sofa {

    @Override
    public String recostarse() {
        return "Te recuestas en un sofa de estilo moderno.";
    }
}
