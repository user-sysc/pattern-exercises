package factory;

import moderna.MesillaModerna;
import moderna.SillaModerna;
import moderna.SofaModerno;
import muebles.Mesilla;
import muebles.Silla;
import muebles.Sofa;

/**
 * Fabrica concreta: produce la familia completa Moderna.
 */
public class FactoryModerna implements MuebleFactory {

    @Override
    public Silla crearSilla() {
        return new SillaModerna();
    }

    @Override
    public Sofa crearSofa() {
        return new SofaModerno();
    }

    @Override
    public Mesilla crearMesilla() {
        return new MesillaModerna();
    }
}
