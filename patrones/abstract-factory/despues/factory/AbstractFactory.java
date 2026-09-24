package factory;

import service.ConsultorService;
import service.NotificacionService;
import service.ReporteService;

/**
 * Fabrica abstracta: declara la creacion de TODA la familia de productos.
 * Cada fabrica concreta garantiza que los 3 servicios sean del mismo proveedor.
 */
public interface AbstractFactory {

    ConsultorService crearConsultor();

    ReporteService crearReporte();

    NotificacionService crearNotificacion();
}
