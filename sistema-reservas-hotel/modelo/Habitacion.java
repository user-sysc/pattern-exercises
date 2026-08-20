package modelo;

/**
 * CLASE ABSTRACTA HABITACION
 * Define la estructura base para todas las habitaciones
 * Demuestra: Abstracción, Encapsulamiento, Polimorfismo
 */
public abstract class Habitacion {
    private int numero;
    private double precioPorNoche;
    private EstadoHabitacion estado;
    
    public Habitacion(int numero, double precioPorNoche) {
        this.numero = numero;
        this.precioPorNoche = precioPorNoche;
        this.estado = EstadoHabitacion.DISPONIBLE;
    }
    
    // Método abstracto - cada tipo de habitación lo implementa diferente
    public abstract String getTipo();
    
    // Métodos concretos
    public void reservar() {
        if (estado != EstadoHabitacion.DISPONIBLE) {
            System.out.println("❌ La habitación " + numero + 
                             " no está disponible (Estado: " + estado + ")");
            return;
        }
        estado = EstadoHabitacion.RESERVADA;
        System.out.println("✓ Habitación " + numero + 
                         " (" + getTipo() + ") reservada correctamente.");
    }
    
    public void ocupar() {
        if (estado != EstadoHabitacion.RESERVADA) {
            System.out.println("❌ La habitación " + numero + 
                             " no puede ser ocupada (debe estar reservada)");
            return;
        }
        estado = EstadoHabitacion.OCUPADA;
        System.out.println("✓ Cliente ha hecho check-in en habitación " + numero);
    }
    
    public void liberar() {
        estado = EstadoHabitacion.DISPONIBLE;
        System.out.println("✓ Habitación " + numero + 
                         " está nuevamente disponible.");
    }
    
    // Getters
    public int getNumero() {
        return numero;
    }
    
    public double getPrecioPorNoche() {
        return precioPorNoche;
    }
    
    public EstadoHabitacion getEstado() {
        return estado;
    }
    
    public boolean isDisponible() {
        return estado == EstadoHabitacion.DISPONIBLE;
    }
    
    public String mostrarDetalles() {
        return "Habitación " + numero + 
               " | Tipo: " + getTipo() + 
               " | Precio: $" + String.format("%.2f", precioPorNoche) + 
               "/noche | Estado: " + estado;
    }
}