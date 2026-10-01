package antes;

import antes.controller.ComputadorController;

/**
 * ANTES: armado de computadores sin Builder.
 * El cliente llama a constructores posicionales enormes.
 */
public class AntesMain {

    public static void main(String[] args) {
        ComputadorController controller = new ComputadorController();

        System.out.println("Computador sin Builder ");
        controller.mostrarGamer();
        System.out.println();
        controller.mostrarOficina();
    }
}
