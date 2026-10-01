package domain.equipo;

import java.util.HashMap;
import java.util.Map;

/**
 * ABSTRACT FACTORY - Provider.
 *
 * Mapea el mundo ("medieval", "futurista") con su fabrica de equipamiento.
 */
public class FabricaEquipamientoProvider {

    private final Map<String, FabricaEquipamiento> fabricas = new HashMap<>();

    public FabricaEquipamientoProvider() {
        fabricas.put("medieval", new FabricaMedieval());
        fabricas.put("futurista", new FabricaFuturista());
    }

    public FabricaEquipamiento get(String mundo) {
        FabricaEquipamiento fabrica = fabricas.get(mundo.toLowerCase());
        if (fabrica == null) {
            throw new IllegalArgumentException("Mundo desconocido: " + mundo);
        }
        return fabrica;
    }
}
