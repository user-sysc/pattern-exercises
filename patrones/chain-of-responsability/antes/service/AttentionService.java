package antes.service;

import antes.domain.Request;
import antes.domain.User;
import antes.exception.ForbiddenException;
import antes.exception.UnauthorizedException;

/**
 * Servicio monolítico antes de aplicar Chain of Responsibility.
 *
 * Todas las validaciones y reglas (autenticación, autorización de rol,
 * saneamiento, caché y persistencia) se encuentran fuertemente acopladas
 * dentro de un único método mediante bloques condicionales if/else.
 */
public class AttentionService {

    private final AuthService auth;
    private final User user;
    private final Sanitizer sanitizer;
    private final Cache cache;
    private final Repo repo;

    public AttentionService(AuthService auth, User user, Sanitizer sanitizer,
                            Cache cache, Repo repo) {
        this.auth = auth;
        this.user = user;
        this.sanitizer = sanitizer;
        this.cache = cache;
        this.repo = repo;
    }

    public void processAttention(Request req) {
        // 1. Validación de token (acoplado)
        if (req.getToken() == null || !auth.verify(req.getToken())) {
            throw new UnauthorizedException();
        }

        // 2. Validación de rol (acoplado)
        User currentUser = req.getUser() != null ? req.getUser() : this.user;
        if (currentUser == null || !currentUser.hasRole("NURSE")) {
            throw new ForbiddenException();
        }

        // 3. Saneamiento
        String cleanData = sanitizer.clean(req.getData());
        req.setData(cleanData);

        // 4. Revisión de caché (acoplado)
        if (cache.contains(cleanData)) {
            req.setRespuesta(cache.get(cleanData));
            return;
        }

        // 5. Lógica principal
        repo.save(cleanData);
        req.setRespuesta("Atención registrada con éxito en repositorio: " + cleanData);
    }
}
