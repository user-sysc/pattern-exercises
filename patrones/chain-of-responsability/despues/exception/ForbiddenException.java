package despues.exception;

/**
 * Excepción lanzada cuando el usuario no cuenta con los permisos o rol requerido (HTTP 403).
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("403 Forbidden: Permisos insuficientes. Se requiere el rol 'NURSE'.");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
