package service;

/**
 * Producto abstracto: contrato de consultoria.
 * El cliente depende de esta interfaz, nunca de Grok ni Gemini.
 */
public interface ConsultorService {

    String consultar();
}
