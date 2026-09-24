package client;

import factory.AbstractFactory;
import service.ConsultorService;
import service.NotificacionService;
import service.ReporteService;

/**
 * Cliente DESPUES: solo conoce la fabrica abstracta y las interfaces de servicio.
 * No menciona Grok ni Gemini; la familia se decide una sola vez al inyectar
 * la AbstractFactory.
 */
public class Cliente {

    private ConsultorService consultor;
    private ReporteService reporte;
    private NotificacionService notificacion;

    // Se inyecta la fabrica abstracta (puede ser Grok o Gemini).
    public Cliente(AbstractFactory factory) {
        this.consultor = factory.crearConsultor();
        this.reporte = factory.crearReporte();
        this.notificacion = factory.crearNotificacion();
    }

    public void operarSistema() {
        System.out.println(consultor.consultar());
        System.out.println(reporte.crearReporte());
        System.out.println(notificacion.notificar());
    }
}
