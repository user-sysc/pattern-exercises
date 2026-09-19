package antes.domain;

/**
 * Transporte concreto (lado ANTES).
 * El service lo instancia directamente con "new".
 */
public class Camion {

    public String entregar() {
        return "Entrega por tierra en caja de carga.";
    }
}
