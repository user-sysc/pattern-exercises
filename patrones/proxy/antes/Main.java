package antes;

public class Main {
    public static void main(String[] args) {
        // El cliente crea directamente el objeto real
        DocumentoReal doc =
                new DocumentoReal("HistoriaClinica.pdf", false);

        doc.Acceder();
    }
}
