package despues.handler;

import despues.domain.Request;
import despues.service.Sanitizer;

/**
 * Manejador concreto encargado de limpiar y sanear el contenido de req.getData().
 * Devuelve true para continuar al siguiente eslabón de la cadena.
 */
public class SanitizeHandler extends Handler {

    private final Sanitizer sanitizer;

    public SanitizeHandler(Sanitizer sanitizer) {
        this.sanitizer = sanitizer;
    }

    @Override
    protected boolean procesar(Request req) {
        String cleanData = sanitizer.clean(req.getData());
        req.setData(cleanData);
        return true;
    }
}
