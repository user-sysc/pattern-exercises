package domain.equipo;

/**
 * Fabrica concreta: entrega la familia MEDIEVAL completa y consistente.
 */
public class FabricaMedieval implements FabricaEquipamiento {

    @Override
    public Arma crearArma() {
        return new Espada();
    }

    @Override
    public Armadura crearArmadura() {
        return new ArmaduraPlacas();
    }

    @Override
    public Escudo crearEscudo() {
        return new EscudoMadera();
    }
}
