package modelo;

/**
 * CLASE CLIENTE
 * Representa un cliente del hotel
 * Demuestra: Encapsulamiento
 */
public class Cliente {
    private String nombre;
    private String documento;
    private String email;
    private String telefono;
    
    public Cliente(String nombre, String documento, String email, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
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
    
    public String getTelefono() {
        return telefono;
    }
    
    public void mostrarInformacion() {
        System.out.println("👤 Cliente: " + nombre);
        System.out.println("   Documento: " + documento);
        System.out.println("   Email: " + email);
        System.out.println("   Teléfono: " + telefono);
    }
}