package antes.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Almacenamiento en caché en memoria para evitar recomputar o duplicar solicitudes frecuentes.
 */
public class Cache {

    private final Map<String, String> cacheStorage = new HashMap<>();

    public boolean contains(String key) {
        return key != null && cacheStorage.containsKey(key);
    }

    public String get(String key) {
        return cacheStorage.get(key);
    }

    public void put(String key, String value) {
        cacheStorage.put(key, value);
    }

    public void clear() {
        cacheStorage.clear();
    }
}
