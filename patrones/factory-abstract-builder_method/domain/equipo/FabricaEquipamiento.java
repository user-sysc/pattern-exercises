package domain.equipo;

/**
 * ABSTRACT FACTORY - Fabrica abstracta.
 *
 * Declara la creacion de una FAMILIA completa de productos relacionados
 * (arma + armadura + escudo). Cada fabrica concreta entrega una familia
 * consistente de un mismo mundo.
 */
public interface FabricaEquipamiento {

    Arma crearArma();

    Armadura crearArmadura();

    Escudo crearEscudo();
}
