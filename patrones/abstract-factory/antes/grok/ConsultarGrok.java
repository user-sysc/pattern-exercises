package grok;

import service.ConsultorService;

/**
 * Producto concreto de la familia Grok.
 * En el lado ANTES el Cliente lo instancia directamente con "new".
 */
public class ConsultarGrok implements ConsultorService {

    @Override
    public String consultar() {
        return "Consultando datos mediante el servicio de Grok...";
    }
}
