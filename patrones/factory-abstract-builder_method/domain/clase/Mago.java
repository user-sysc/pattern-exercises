package domain.clase;

/**
 * Producto concreto: personaje de tipo Mago.
 */
public class Mago implements ClasePersonaje {

    @Override
    public String nombre() {
        return "Mago";
    }

    @Override
    public int vidaBase() {
        return 70;
    }

    @Override
    public int manaBase() {
        return 140;
    }

    @Override
    public String habilidad() {
        return "Bola de fuego";
    }
}
