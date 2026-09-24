package factory;

import gemini.ConsultarGemini;
import gemini.NotificacionGemini;
import gemini.ReporteGemini;
import service.ConsultorService;
import service.NotificacionService;
import service.ReporteService;

/**
 * Fabrica concreta: produce la familia completa de Gemini.
 */
public class FactoryGemini implements AbstractFactory {

    @Override
    public ConsultorService crearConsultor() {
        return new ConsultarGemini();
    }

    @Override
    public ReporteService crearReporte() {
        return new ReporteGemini();
    }

    @Override
    public NotificacionService crearNotificacion() {
        return new NotificacionGemini();
    }
}
