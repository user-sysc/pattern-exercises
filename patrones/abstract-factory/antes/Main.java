import client.Cliente;

/**
 * ANTES: el cliente decide con un String y el Cliente arma la familia
 * con un if/else instanciando clases concretas.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== ANTES: sin Abstract Factory ===");

        System.out.println("--- USANDO LA FAMILIA GROK ---");
        Cliente clienteGrok = new Cliente("grok");
        clienteGrok.operarSistema();

        System.out.println("\n--- USANDO LA FAMILIA GEMINI ---");
        Cliente clienteGemini = new Cliente("gemini");
        clienteGemini.operarSistema();
    }
}
