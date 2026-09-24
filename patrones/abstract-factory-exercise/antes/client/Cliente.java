package client;

import artdeco.MesillaArtDeco;
import artdeco.SillaArtDeco;
import artdeco.SofaArtDeco;
import moderna.MesillaModerna;
import moderna.SillaModerna;
import moderna.SofaModerno;
import muebles.Mesilla;
import muebles.Silla;
import muebles.Sofa;
import victoriana.MesillaVictoriana;
import victoriana.SillaVictoriana;
import victoriana.SofaVictoriano;

/**
 * Cliente ANTES: sin Abstract Factory.
 *
 * Problemas de esta version:
 *  - Acoplamiento fuerte: conoce y crea TODAS las clases concretas.
 *  - Viola Open/Closed (OCP): agregar una variante nueva (p. ej. Rustica)
 *    obliga a editar este if/else.
 *  - Permite mezclar familias si se modifica mal (silla moderna + sofa victoriano).
 */
public class Cliente {

    private Silla silla;
    private Sofa sofa;
    private Mesilla mesilla;

    public Cliente(String estilo) {
        if (estilo.equals("moderna")) {
            silla = new SillaModerna();
            sofa = new SofaModerno();
            mesilla = new MesillaModerna();
        } else if (estilo.equals("victoriana")) {
            silla = new SillaVictoriana();
            sofa = new SofaVictoriano();
            mesilla = new MesillaVictoriana();
        } else if (estilo.equals("artdeco")) {
            silla = new SillaArtDeco();
            sofa = new SofaArtDeco();
            mesilla = new MesillaArtDeco();
        } else {
            throw new IllegalArgumentException("Estilo desconocido: " + estilo);
        }
    }

    public void amoblarSala() {
        System.out.println(silla.sentarse());
        System.out.println(sofa.recostarse());
        System.out.println(mesilla.colocar());
    }
}
