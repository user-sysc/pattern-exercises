package despues.exception;

/**
 * Excepción lanzada cuando el token de autenticación no es válido o está ausente (HTTP 401).
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("401 Unauthorized: Token de autenticación inválido o ausente.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
