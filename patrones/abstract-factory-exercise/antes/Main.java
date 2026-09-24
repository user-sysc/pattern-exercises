import client.Cliente;

/**
 * ANTES: el cliente decide con un String y el Cliente arma la familia
 * con un if/else instanciando clases concretas.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== ANTES: sin Abstract Factory ===");

        System.out.println("--- FAMILIA MODERNA ---");
        Cliente clienteModerno = new Cliente("moderna");
        clienteModerno.amoblarSala();

        System.out.println("\n--- FAMILIA VICTORIANA ---");
        Cliente clienteVictoriano = new Cliente("victoriana");
        clienteVictoriano.amoblarSala();

        System.out.println("\n--- FAMILIA ART DECO ---");
        Cliente clienteArtDeco = new Cliente("artdeco");
        clienteArtDeco.amoblarSala();
    }
}
