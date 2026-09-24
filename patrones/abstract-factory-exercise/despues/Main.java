import client.Cliente;
import factory.FactoryArtDeco;
import factory.FactoryModerna;
import factory.FactoryVictoriana;
import factory.MuebleFactory;

/**
 * DESPUES: el Main elige la familia inyectando la fabrica.
 * El Cliente nunca nombra Moderna, Victoriana ni ArtDeco.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== DESPUES: con Abstract Factory ===");

        System.out.println("--- FAMILIA MODERNA ---");
        MuebleFactory factoryModerna = new FactoryModerna();
        Cliente clienteModerno = new Cliente(factoryModerna);
        clienteModerno.amoblarSala();

        System.out.println("\n--- FAMILIA VICTORIANA ---");
        MuebleFactory factoryVictoriana = new FactoryVictoriana();
        Cliente clienteVictoriano = new Cliente(factoryVictoriana);
        clienteVictoriano.amoblarSala();

        System.out.println("\n--- FAMILIA ART DECO ---");
        MuebleFactory factoryArtDeco = new FactoryArtDeco();
        Cliente clienteArtDeco = new Cliente(factoryArtDeco);
        clienteArtDeco.amoblarSala();
    }
}
