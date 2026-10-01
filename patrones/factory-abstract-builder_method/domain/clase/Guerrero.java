package domain.clase;

/**
 * Producto concreto: personaje de tipo Guerrero.
 */
public class Guerrero implements ClasePersonaje {

    @Override
    public String nombre() {
        return "Guerrero";
    }

    @Override
    public int vidaBase() {
        return 120;
    }

    @Override
    public int manaBase() {
        return 20;
    }

    @Override
    public String habilidad() {
        return "Golpe critico";
    }
}
