package despues.domain.factory;

import despues.domain.Camion;
import despues.domain.Transporte;

/**
 * Creador concreto: decide que se crea un Camion.
 */
public class CamionFactory extends TransporteFactory {

    @Override
    public Transporte createTransporte() {
        return new Camion();
    }
}
