package despues.handler;

import despues.domain.Request;
import despues.service.Repo;

/**
 * Manejador concreto terminal (último eslabón de la cadena de atención).
 * Guarda los datos validados y saneados en el repositorio de persistencia.
 */
public class AttentionHandler extends Handler {

    private final Repo repo;

    public AttentionHandler(Repo repo) {
        this.repo = repo;
    }

    @Override
    protected boolean procesar(Request req) {
        repo.save(req.getData());
        req.setRespuesta("Atención registrada con éxito en repositorio: " + req.getData());
        return true;
    }
}
