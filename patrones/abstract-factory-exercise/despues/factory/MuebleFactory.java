package factory;

import muebles.Mesilla;
import muebles.Silla;
import muebles.Sofa;

/**
 * Fabrica abstracta: declara la creacion de TODA la familia de productos.
 * Cada fabrica concreta garantiza que los 3 muebles sean de la misma variante.
 */
public interface MuebleFactory {

    Silla crearSilla();

    Sofa crearSofa();

    Mesilla crearMesilla();
}
