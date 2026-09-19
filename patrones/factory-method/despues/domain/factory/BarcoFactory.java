package despues.domain.factory;

import despues.domain.Barco;
import despues.domain.Transporte;

/**
 * Creador concreto: decide que se crea un Barco.
 */
public class BarcoFactory extends TransporteFactory {

    @Override
    public Transporte createTransporte() {
        return new Barco();
    }
}
