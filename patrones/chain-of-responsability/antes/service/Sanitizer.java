package antes.service;

/**
 * Componente encargado de limpiar y sanear los datos sensibles de la solicitud
 * eliminando etiquetas o caracteres no permitidos y espacios redundantes.
 */
public class Sanitizer {

    public String clean(String data) {
        if (data == null) {
            return "";
        }
        return data.replaceAll("<[^>]*>", "").trim().replaceAll("\\s+", " ");
    }
}
