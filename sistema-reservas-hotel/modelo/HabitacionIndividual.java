package modelo;

/**
 * HABITACIÓN INDIVIDUAL
 * Hereda de Habitacion
 * Demuestra: Herencia
 */
public class HabitacionIndividual extends Habitacion {
    public HabitacionIndividual(int numero, double precioPorNoche) {
        super(numero, precioPorNoche);
    }
    
    @Override
    public String getTipo() {
        return "Individual (1 persona)";
    }
}