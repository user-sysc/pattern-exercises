package antes;

import antes.controller.LogisticaController;

/**
 * ANTES: Logistica sin Factory Method.
 * El cliente pide por un String y el service decide instanciando clases concretas.
 */
public class AntesMain {

    public static void main(String[] args) {
        LogisticaController controller = new LogisticaController();

        System.out.println("=== ANTES: Logistica sin Factory Method ===");
        controller.planificar("tierra");
        controller.planificar("mar");
    }
}
