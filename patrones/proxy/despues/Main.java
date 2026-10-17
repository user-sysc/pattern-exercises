package despues;

public class Main {
    public static void main(String[] args) {
        Documento doc = new ElementoProxy(
                "HistoriaClinica.pdf", false
        );

        doc.Acceder();

        System.out.println("--- Segundo intento ---");

        doc = new ElementoProxy(
                "HistoriaClinica.pdf", true
        );

        doc.Acceder();
    }
}
