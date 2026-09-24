package grok;

import service.NotificacionService;

/**
 * Producto concreto de la familia Grok.
 */
public class NotificacionGrok implements NotificacionService {

    @Override
    public String notificar() {
        return "Enviando notificación a través de la red de Grok...";
    }
}
