package despues.domain.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Provider de fabricas: mapea el tipo de transporte con su fabrica.
 * El service ya no decide con if/else, solo pide la fabrica por clave.
 */
public class TransporteProvider {

    private final Map<String, TransporteFactory> factories = new HashMap<>();

    public TransporteProvider() {
        factories.put("tierra", new CamionFactory());
        factories.put("mar", new BarcoFactory());
    }

    public TransporteFactory getFactory(String tipo) {
        TransporteFactory factory = factories.get(tipo);
        if (factory == null) {
            throw new IllegalArgumentException("Tipo de transporte desconocido: " + tipo);
        }
        return factory;
    }
}
