package despues.domain.factory;

import despues.domain.Transporte;

/**
 * Creador abstracto (Factory Method).
 * Declara createTransporte() y expone getTransporte() como template.
 */
public abstract class TransporteFactory {

    public Transporte getTransporte() {
        return createTransporte();
    }

    public abstract Transporte createTransporte();
}
