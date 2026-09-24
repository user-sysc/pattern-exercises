package muebles;

/**
 * Producto abstracto: contrato de una silla.
 * El cliente depende de esta interfaz, no de la variante concreta.
 */
public interface Silla {

    String sentarse();
}
