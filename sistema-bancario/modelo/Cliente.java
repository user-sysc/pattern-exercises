package modelo;

/**
 * CLASE CLIENTE
 * Representa un cliente del banco
 * Demuestra: Encapsulamiento
 */
public class Cliente {
    private String nombre;
    private String documento;
    private String email;
    
    public Cliente(String nombre, String documento, String email) {
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombre);
        System.out.println("Documento: " + documento);
        System.out.println("Email: " + email);
    }
}