package despues.controller;

import despues.domain.Computador;
import despues.domain.builder.ComputadorBuilder;
import despues.domain.builder.ComputadorGamerBuilder;
import despues.domain.builder.ComputadorOficinaBuilder;
import despues.domain.builder.EnsambladorDirector;

/**
 * Controlador del lado DESPUES.
 * Elige el Builder concreto y deja que el director arme el objeto paso a paso.
 * El cliente no conoce constructores gigantes ni el orden de las partes.
 */
public class ComputadorController {

    private final EnsambladorDirector director = new EnsambladorDirector();

    public void mostrarGamer() {
        Computador pc = ensamblar(new ComputadorGamerBuilder());
        System.out.println("--- PC Gamer ---");
        System.out.println(pc);
    }

    public void mostrarOficina() {
        Computador pc = ensamblar(new ComputadorOficinaBuilder());
        System.out.println("--- PC de Oficina ---");
        System.out.println(pc);
    }

    private Computador ensamblar(ComputadorBuilder builder) {
        return director.ensamblar(builder);
    }
}
