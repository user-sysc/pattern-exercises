package despues.domain.builder;

import despues.domain.Computador;

/**
 * Director: conoce el ORDEN en que se arman las partes.
 *
 * El cliente le pasa un Builder concreto y el director ejecuta siempre la misma
 * secuencia. El cliente no repite la logica de construccion ni conoce el orden
 * de los pasos.
 */
public class EnsambladorDirector {

    public Computador ensamblar(ComputadorBuilder builder) {
        builder.construirCpu();
        builder.construirRam();
        builder.construirGpu();
        builder.construirAlmacenamiento();
        builder.construirFuente();
        builder.configurarConectividad();
        builder.configurarRefrigeracion();
        builder.configurarSoftware();
        return builder.getResultado();
    }
}
