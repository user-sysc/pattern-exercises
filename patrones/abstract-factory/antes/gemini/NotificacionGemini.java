package gemini;

import service.NotificacionService;

/**
 * Producto concreto de la familia Gemini.
 */
public class NotificacionGemini implements NotificacionService {

    @Override
    public String notificar() {
        return "Enviando notificación a través de la red de Gemini...";
    }
}
