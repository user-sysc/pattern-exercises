package artdeco;

import muebles.Mesilla;

/**
 * Producto concreto de la familia ArtDeco.
 */
public class MesillaArtDeco implements Mesilla {

    @Override
    public String colocar() {
        return "Colocas un objeto sobre una mesilla de estilo art deco.";
    }
}
