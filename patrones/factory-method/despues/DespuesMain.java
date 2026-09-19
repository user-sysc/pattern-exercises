package despues;

import despues.controller.LogisticaController;

/**
 * DESPUES: Logistica con Factory Method + Provider.
 *
 * El main pide por tipo; el provider resuelve la fabrica y nadie mas
 * conoce las clases concretas.
 */
public class DespuesMain {

    public static void main(String[] args) {
        LogisticaController controller = new LogisticaController();

        System.out.println("=== DESPUES: Logistica con Factory Method ===");

        System.out.println("--- Entrega terrestre ---");
        controller.planificar("tierra");

        System.out.println("--- Entrega maritima ---");
        controller.planificar("mar");
    }
}
