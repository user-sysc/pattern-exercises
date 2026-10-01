package domain.equipo;

/**
 * Fabrica concreta: entrega la familia FUTURISTA completa y consistente.
 */
public class FabricaFuturista implements FabricaEquipamiento {

    @Override
    public Arma crearArma() {
        return new PistolaLaser();
    }

    @Override
    public Armadura crearArmadura() {
        return new ArmaduraTecnologica();
    }

    @Override
    public Escudo crearEscudo() {
        return new EscudoEnergetico();
    }
}
