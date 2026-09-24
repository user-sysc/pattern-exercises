package grok;

import service.ConsultorService;

/**
 * Producto concreto de la familia Grok.
 * Solo la FactoryGrok lo conoce: el Cliente nunca lo instancia.
 */
public class ConsultarGrok implements ConsultorService {

    @Override
    public String consultar() {
        return "Consultando datos mediante el servicio de Grok...";
    }
}
