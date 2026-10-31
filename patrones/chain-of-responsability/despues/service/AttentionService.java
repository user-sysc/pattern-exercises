package despues.service;

import despues.domain.Request;
import despues.domain.User;
import despues.handler.AttentionHandler;
import despues.handler.AuthHandler;
import despues.handler.CacheHandler;
import despues.handler.Handler;
import despues.handler.RoleHandler;
import despues.handler.SanitizeHandler;

/**
 * Servicio refactorizado utilizando el patrón Chain of Responsibility.
 *
 * AttentionService ya no conoce las reglas individuales ni los detalles de cada validación.
 * Su única responsabilidad es delegar la solicitud al primer eslabón de la cadena.
 */
public class AttentionService {

    private final Handler cadena;

    public AttentionService(AuthService auth, User user, Sanitizer sanitizer,
                            Cache cache, Repo repo) {
        Handler primero = new AuthHandler(auth);
        primero.setNext(new RoleHandler(user))
               .setNext(new SanitizeHandler(sanitizer))
               .setNext(new CacheHandler(cache))
               .setNext(new AttentionHandler(repo));
        this.cadena = primero;
    }

    public AttentionService(Handler cadena) {
        this.cadena = cadena;
    }

    public void processAttention(Request req) {
        cadena.handle(req);
    }
}
