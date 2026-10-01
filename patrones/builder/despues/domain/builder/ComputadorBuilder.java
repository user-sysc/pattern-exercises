package despues.domain.builder;

import despues.domain.Computador;

/**
 * Builder: declara los pasos para armar un Computador.
 *
 * El Director conoce el ORDEN de estos pasos; cada Builder concreto decide los
 * VALORES de cada parte. Asi el mismo proceso de construccion puede producir
 * representaciones distintas (gamer, oficina, ...).
 */
public interface ComputadorBuilder {

    void construirCpu();

    void construirRam();

    void construirGpu();

    void construirAlmacenamiento();

    void construirFuente();

    void configurarConectividad();

    void configurarRefrigeracion();

    void configurarSoftware();

    Computador getResultado();
}
