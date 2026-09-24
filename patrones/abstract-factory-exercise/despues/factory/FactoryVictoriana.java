package factory;

import muebles.Mesilla;
import muebles.Silla;
import muebles.Sofa;
import victoriana.MesillaVictoriana;
import victoriana.SillaVictoriana;
import victoriana.SofaVictoriano;

/**
 * Fabrica concreta: produce la familia completa Victoriana.
 */
public class FactoryVictoriana implements MuebleFactory {

    @Override
    public Silla crearSilla() {
        return new SillaVictoriana();
    }

    @Override
    public Sofa crearSofa() {
        return new SofaVictoriano();
    }

    @Override
    public Mesilla crearMesilla() {
        return new MesillaVictoriana();
    }
}
