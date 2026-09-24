package gemini;

import service.ConsultorService;

/**
 * Producto concreto de la familia Gemini.
 * En el lado ANTES el Cliente lo instancia directamente con "new".
 */
public class ConsultarGemini implements ConsultorService {

    @Override
    public String consultar() {
        return "Consultando datos mediante el servicio de Gemini...";
    }
}
