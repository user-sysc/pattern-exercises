package gemini;

import service.ConsultorService;

/**
 * Producto concreto de la familia Gemini.
 * Solo la FactoryGemini lo conoce: el Cliente nunca lo instancia.
 */
public class ConsultarGemini implements ConsultorService {

    @Override
    public String consultar() {
        return "Consultando datos mediante el servicio de Gemini...";
    }
}
