package modelo;

/**
 * SUITE
 * Hereda de Habitacion
 * Demuestra: Herencia
 */
public class Suite extends Habitacion {
    public Suite(int numero, double precioPorNoche) {
        super(numero, precioPorNoche);
    }
    
    @Override
    public String getTipo() {
        return "Suite (Lujo, 2+ personas)";
    }
}