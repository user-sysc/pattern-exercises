package moderna;

import muebles.Silla;

/**
 * Producto concreto de la familia Moderna.
 * Lo crea FactoryModerna.
 */
public class SillaModerna implements Silla {

    @Override
    public String sentarse() {
        return "Te sientas en una silla de estilo moderno.";
    }
}
