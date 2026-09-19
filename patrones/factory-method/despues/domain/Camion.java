package despues.domain;

/**
 * Producto concreto: implementa Transporte.
 */
public class Camion implements Transporte {

    @Override
    public String entregar() {
        return "Entrega por tierra en caja de carga.";
    }
}
