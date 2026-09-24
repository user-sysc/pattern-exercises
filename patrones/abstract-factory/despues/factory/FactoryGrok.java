package factory;

import grok.ConsultarGrok;
import grok.NotificacionGrok;
import grok.ReporteGrok;
import service.ConsultorService;
import service.NotificacionService;
import service.ReporteService;

/**
 * Fabrica concreta: produce la familia completa de Grok.
 */
public class FactoryGrok implements AbstractFactory {

    @Override
    public ConsultorService crearConsultor() {
        return new ConsultarGrok();
    }

    @Override
    public ReporteService crearReporte() {
        return new ReporteGrok();
    }

    @Override
    public NotificacionService crearNotificacion() {
        return new NotificacionGrok();
    }
}
