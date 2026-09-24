package grok;

import service.ReporteService;

/**
 * Producto concreto de la familia Grok.
 */
public class ReporteGrok implements ReporteService {

    @Override
    public String crearReporte() {
        return "Generando reporte analítico con Grok...";
    }
}
