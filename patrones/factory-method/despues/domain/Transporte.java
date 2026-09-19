package despues.domain;

/**
 * Producto: interfaz comun que devuelve el Factory Method.
 * El resto del codigo depende de esta abstraccion, no de las clases concretas.
 */
public interface Transporte {

    String entregar();
}
