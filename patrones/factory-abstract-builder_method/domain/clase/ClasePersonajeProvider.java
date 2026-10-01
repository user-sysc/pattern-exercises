package domain.clase;

import java.util.HashMap;
import java.util.Map;

/**
 * FACTORY METHOD - Provider.
 *
 * Mapea el tipo de personaje ("guerrero", "mago", "arquero") con su creador.
 * El service no decide con if/else, solo pide el creador por clave.
 */
public class ClasePersonajeProvider {

    private final Map<String, CreadorClasePersonaje> creadores = new HashMap<>();

    public ClasePersonajeProvider() {
        creadores.put("guerrero", new CreadorGuerrero());
        creadores.put("mago", new CreadorMago());
        creadores.put("arquero", new CreadorArquero());
    }

    public CreadorClasePersonaje get(String tipo) {
        CreadorClasePersonaje creador = creadores.get(tipo.toLowerCase());
        if (creador == null) {
            throw new IllegalArgumentException("Tipo de personaje desconocido: " + tipo);
        }
        return creador;
    }
}
