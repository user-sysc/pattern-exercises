package antes.service;

/**
 * Servicio encargado de verificar la autenticidad y validez del token recibido.
 */
public class AuthService {

    private final String validToken;

    public AuthService() {
        this("token-valido-123");
    }

    public AuthService(String validToken) {
        this.validToken = validToken;
    }

    public boolean verify(String token) {
        return token != null && token.equals(this.validToken);
    }
}
