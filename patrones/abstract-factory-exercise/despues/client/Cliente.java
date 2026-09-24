package client;

import factory.MuebleFactory;
import muebles.Mesilla;
import muebles.Silla;
import muebles.Sofa;

/**
 * Cliente DESPUES: solo conoce la fabrica abstracta y las interfaces de muebles.
 * No menciona Moderna, Victoriana ni ArtDeco; la familia se decide una sola vez
 * al inyectar la MuebleFactory.
 */
public class Cliente {

    private Silla silla;
    private Sofa sofa;
    private Mesilla mesilla;

    // Se inyecta la fabrica abstracta (puede ser Moderna, Victoriana o ArtDeco).
    public Cliente(MuebleFactory factory) {
        this.silla = factory.crearSilla();
        this.sofa = factory.crearSofa();
        this.mesilla = factory.crearMesilla();
    }

    public void amoblarSala() {
        System.out.println(silla.sentarse());
        System.out.println(sofa.recostarse());
        System.out.println(mesilla.colocar());
    }
}
