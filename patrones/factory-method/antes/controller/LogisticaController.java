package antes.controller;

import antes.services.LogisticaService;

/**
 * Controlador ANTES: recibe la peticion y delega en el service.
 * El acoplamiento con las clases concretas vive en el service.
 */
public class LogisticaController {

    private final LogisticaService logisticaService = new LogisticaService();

    public void planificar(String tipoTransporte) {
        String resultado = logisticaService.planificarEntrega(tipoTransporte);
        System.out.println("Resultado: " + resultado);
    }
}
