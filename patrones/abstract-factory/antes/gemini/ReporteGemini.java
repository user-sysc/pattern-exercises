package gemini;

import service.ReporteService;

/**
 * Producto concreto de la familia Gemini.
 */
public class ReporteGemini implements ReporteService {

    @Override
    public String crearReporte() {
        return "Generando reporte analítico con Gemini...";
    }
}
