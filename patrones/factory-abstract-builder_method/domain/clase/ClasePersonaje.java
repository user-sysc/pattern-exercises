package domain.clase;

/**
 * FACTORY METHOD - Producto.
 *
 * Representa el TIPO de personaje (clase de rol). Cada implementacion define
 * sus estadisticas base y su habilidad especial.
 */
public interface ClasePersonaje {

    String nombre();

    int vidaBase();

    int manaBase();

    String habilidad();
}
