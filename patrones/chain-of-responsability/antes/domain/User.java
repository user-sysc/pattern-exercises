package antes.domain;

/**
 * Representa al usuario que realiza o solicita la operación en el sistema de salud.
 */
public class User {

    private final String username;
    private final String role;

    public User(String role) {
        this("usuario_anonimo", role);
    }

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public boolean hasRole(String expectedRole) {
        return this.role != null && this.role.equalsIgnoreCase(expectedRole);
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', role='" + role + "'}";
    }
}
