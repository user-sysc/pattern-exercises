package factory;

import artdeco.MesillaArtDeco;
import artdeco.SillaArtDeco;
import artdeco.SofaArtDeco;
import muebles.Mesilla;
import muebles.Silla;
import muebles.Sofa;

/**
 * Fabrica concreta: produce la familia completa ArtDeco.
 */
public class FactoryArtDeco implements MuebleFactory {

    @Override
    public Silla crearSilla() {
        return new SillaArtDeco();
    }

    @Override
    public Sofa crearSofa() {
        return new SofaArtDeco();
    }

    @Override
    public Mesilla crearMesilla() {
        return new MesillaArtDeco();
    }
}
