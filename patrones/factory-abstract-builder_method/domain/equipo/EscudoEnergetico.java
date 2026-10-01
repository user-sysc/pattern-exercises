package domain.equipo;

/**
 * Producto concreto de la familia FUTURISTA.
 */
public class EscudoEnergetico implements Escudo {

    @Override
    public String nombre() {
        return "Escudo energetico";
    }

    @Override
    public int bloqueo() {
        return 10;
    }
}
