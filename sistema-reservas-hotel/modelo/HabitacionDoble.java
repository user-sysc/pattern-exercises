package modelo;

/**
 * HABITACIÓN DOBLE
 * Hereda de Habitacion
 * Demuestra: Herencia
 */
public class HabitacionDoble extends Habitacion {
    public HabitacionDoble(int numero, double precioPorNoche) {
        super(numero, precioPorNoche);
    }
    
    @Override
    public String getTipo() {
        return "Doble (2 personas)";
    }
}