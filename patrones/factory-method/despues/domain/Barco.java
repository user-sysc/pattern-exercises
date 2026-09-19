package despues.domain;

/**
 * Producto concreto: implementa Transporte.
 */
public class Barco implements Transporte {

    @Override
    public String entregar() {
        return "Entrega por mar en contenedor maritimo.";
    }
}
