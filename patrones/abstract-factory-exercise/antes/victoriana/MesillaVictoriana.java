package victoriana;

import muebles.Mesilla;

/**
 * Producto concreto de la familia Victoriana.
 */
public class MesillaVictoriana implements Mesilla {

    @Override
    public String colocar() {
        return "Colocas un objeto sobre una mesilla de estilo victoriano.";
    }
}
