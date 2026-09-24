import client.Cliente;
import factory.AbstractFactory;
import factory.FactoryGemini;
import factory.FactoryGrok;

/**
 * DESPUES: el Main elige la familia inyectando la fabrica.
 * El Cliente nunca nombra Grok ni Gemini.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== DESPUES: con Abstract Factory ===");

        System.out.println("--- USANDO LA FAMILIA GROK ---");
        AbstractFactory factoryGrok = new FactoryGrok();
        Cliente clienteGrok = new Cliente(factoryGrok);
        clienteGrok.operarSistema();

        System.out.println("\n--- USANDO LA FAMILIA GEMINI ---");
        AbstractFactory factoryGemini = new FactoryGemini();
        Cliente clienteGemini = new Cliente(factoryGemini);
        clienteGemini.operarSistema();
    }
}
