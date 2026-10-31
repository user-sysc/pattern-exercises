package despues.handler;

import despues.domain.Request;
import despues.exception.UnauthorizedException;
import despues.service.AuthService;

/**
 * Manejador concreto encargado de validar la autenticidad del token de la solicitud.
 * Si el token es inválido o no existe, lanza UnauthorizedException (HTTP 401).
 */
public class AuthHandler extends Handler {

    private final AuthService auth;

    public AuthHandler(AuthService auth) {
        this.auth = auth;
    }

    @Override
    protected boolean procesar(Request req) {
        if (req.getToken() == null || !auth.verify(req.getToken())) {
            throw new UnauthorizedException();
        }
        return true;
    }
}
