import controller.PersonajeController;

/**
 * Sistema de creacion de personajes combinando los tres patrones:
 *
 *   - Factory Method   -> que tipo de personaje crear (guerrero/mago/arquero).
 *   - Abstract Factory -> que familia de equipo usar (medieval/futurista).
 *   - Builder          -> como ensamblar el personaje final.
 */
public class Main {

    public static void main(String[] args) {
        PersonajeController controller = new PersonajeController();

        System.out.println("=== Sistema de creacion de personajes ===");
        System.out.println("(Factory Method + Abstract Factory + Builder)");
        System.out.println();

        controller.crearYMostrar("guerrero", "medieval", "Arthas", 10);
        controller.crearYMostrar("mago", "futurista", "Nova", 8);
        controller.crearYMostrar("arquero", "medieval", "Robin", 5);
    }
}
