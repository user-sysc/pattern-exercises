package despues.services;

import despues.domain.Transporte;
import despues.domain.factory.TransporteFactory;
import despues.domain.factory.TransporteProvider;

/**
 * Service DESPUES: no decide la fabrica ni conoce Camion o Barco.
 * El provider resuelve el tipo y el service trabaja contra la abstraccion.
 */
public class LogisticaService {

    private final TransporteProvider provider = new TransporteProvider();

    public String planificarEntrega(String tipo) {
        TransporteFactory factory = provider.getFactory(tipo);
        Transporte transporte = factory.getTransporte();
        return transporte.entregar();
    }
}
