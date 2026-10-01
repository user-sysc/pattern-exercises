package domain.equipo;

/**
 * Producto concreto de la familia MEDIEVAL.
 */
public class EscudoMadera implements Escudo {

    @Override
    public String nombre() {
        return "Escudo de madera";
    }

    @Override
    public int bloqueo() {
        return 8;
    }
}
