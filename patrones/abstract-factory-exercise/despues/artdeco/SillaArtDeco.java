package artdeco;

import muebles.Silla;

/**
 * Producto concreto de la familia ArtDeco.
 */
public class SillaArtDeco implements Silla {

    @Override
    public String sentarse() {
        return "Te sientas en una silla de estilo art deco.";
    }
}
