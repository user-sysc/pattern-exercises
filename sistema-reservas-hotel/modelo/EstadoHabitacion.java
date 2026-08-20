package modelo;

/**
 * ENUM PARA ESTADOS
 */
public enum EstadoHabitacion {
    DISPONIBLE("Disponible"),
    RESERVADA("Reservada"),
    OCUPADA("Ocupada");
    
    private String nombre;
    
    EstadoHabitacion(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}