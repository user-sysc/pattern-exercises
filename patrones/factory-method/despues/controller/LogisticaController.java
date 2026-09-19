package despues.controller;

import despues.services.LogisticaService;

/**
 * Controlador DESPUES: delega en el service.
 * No conoce fabricas, ni Camion, ni Barco.
 */
public class LogisticaController {

    private final LogisticaService logisticaService = new LogisticaService();

    public void planificar(String tipo) {
        System.out.println("Resultado: " + logisticaService.planificarEntrega(tipo));
    }
}
