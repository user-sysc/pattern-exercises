package client;

import gemini.ConsultarGemini;
import gemini.NotificacionGemini;
import gemini.ReporteGemini;
import grok.ConsultarGrok;
import grok.NotificacionGrok;
import grok.ReporteGrok;
import service.ConsultorService;
import service.NotificacionService;
import service.ReporteService;

/**
 * Cliente ANTES: sin Abstract Factory.
 *
 * Problemas de esta version:
 *  - Acoplamiento fuerte: conoce y crea TODAS las clases concretas.
 *  - Viola Open/Closed (OCP): agregar un proveedor nuevo (p. ej. Claude)
 *    obliga a editar este if/else.
 *  - Permite mezclar familias si se modifica mal (consultor Grok + reporte Gemini).
 */
public class Cliente {

    private ConsultorService consultor;
    private ReporteService reporte;
    private NotificacionService notificacion;

    public Cliente(String proveedor) {
        if (proveedor.equals("grok")) {
            consultor = new ConsultarGrok();
            reporte = new ReporteGrok();
            notificacion = new NotificacionGrok();
        } else if (proveedor.equals("gemini")) {
            consultor = new ConsultarGemini();
            reporte = new ReporteGemini();
            notificacion = new NotificacionGemini();
        } else {
            throw new IllegalArgumentException("Proveedor desconocido: " + proveedor);
        }
    }

    public void operarSistema() {
        System.out.println(consultor.consultar());
        System.out.println(reporte.crearReporte());
        System.out.println(notificacion.notificar());
    }
}
