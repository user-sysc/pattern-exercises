package artdeco;

import muebles.Sofa;

/**
 * Producto concreto de la familia ArtDeco.
 */
public class SofaArtDeco implements Sofa {

    @Override
    public String recostarse() {
        return "Te recuestas en un sofa de estilo art deco.";
    }
}
