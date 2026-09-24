package moderna;

import muebles.Mesilla;

/**
 * Producto concreto de la familia Moderna.
 */
public class MesillaModerna implements Mesilla {

    @Override
    public String colocar() {
        return "Colocas un objeto sobre una mesilla de estilo moderno.";
    }
}
