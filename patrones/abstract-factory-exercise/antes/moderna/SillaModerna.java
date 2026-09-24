package moderna;

import muebles.Silla;

/**
 * Producto concreto de la familia Moderna.
 * En el lado ANTES el Cliente lo instancia directamente con "new".
 */
public class SillaModerna implements Silla {

    @Override
    public String sentarse() {
        return "Te sientas en una silla de estilo moderno.";
    }
}
