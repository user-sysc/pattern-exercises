package despues.handler;

import despues.domain.Request;
import despues.service.Cache;

/**
 * Manejador concreto encargado de consultar el caché en memoria.
 * Si el dato ya fue procesado y se encuentra en caché, asigna la respuesta
 * y retorna false, cortando la cadena para evitar accesos redundantes al repositorio.
 */
public class CacheHandler extends Handler {

    private final Cache cache;

    public CacheHandler(Cache cache) {
        this.cache = cache;
    }

    @Override
    protected boolean procesar(Request req) {
        if (cache.contains(req.getData())) {
            req.setRespuesta(cache.get(req.getData()));
            return false; // corta la cadena
        }
        return true;
    }
}
