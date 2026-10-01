package domain.equipo;

/**
 * Producto concreto de la familia FUTURISTA.
 */
public class ArmaduraTecnologica implements Armadura {

    @Override
    public String nombre() {
        return "Armadura tecnologica";
    }

    @Override
    public int defensa() {
        return 15;
    }
}
