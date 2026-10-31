package despues.handler;

import despues.domain.Request;
import despues.domain.User;
import despues.exception.ForbiddenException;

/**
 * Manejador concreto encargado de verificar la autorización por rol.
 * Requiere que el usuario cuente con el rol 'NURSE'.
 * Si no cumple, lanza ForbiddenException (HTTP 403).
 */
public class RoleHandler extends Handler {

    private final User user;

    public RoleHandler(User user) {
        this.user = user;
    }

    @Override
    protected boolean procesar(Request req) {
        User targetUser = (req.getUser() != null) ? req.getUser() : this.user;
        if (targetUser == null || !targetUser.hasRole("NURSE")) {
            throw new ForbiddenException();
        }
        return true;
    }
}
