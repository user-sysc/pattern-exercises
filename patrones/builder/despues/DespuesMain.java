package despues;

import despues.controller.ComputadorController;

/**
 * DESPUES: armado de computadores con Builder + Director.
 *
 * El cliente solo elige el Builder concreto; el director ejecuta siempre la
 * misma secuencia de construccion.
 */
public class DespuesMain {

    public static void main(String[] args) {
        ComputadorController controller = new ComputadorController();

        System.out.println("=== DESPUES: Computador con Builder ===");
        controller.mostrarGamer();
        System.out.println();
        controller.mostrarOficina();
    }
}
