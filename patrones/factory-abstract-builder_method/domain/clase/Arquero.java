package domain.clase;

/**
 * Producto concreto: personaje de tipo Arquero.
 */
public class Arquero implements ClasePersonaje {

    @Override
    public String nombre() {
        return "Arquero";
    }

    @Override
    public int vidaBase() {
        return 90;
    }

    @Override
    public int manaBase() {
        return 60;
    }

    @Override
    public String habilidad() {
        return "Disparo certero";
    }
}
