package antes.services;

import antes.domain.Barco;
import antes.domain.Camion;

/**
 * el service conoce y crea todas las clases concretas.
 *
 * Problemas:
 *  - Acoplamiento fuerte: depende de Camion y Barco.
 *  - Agregar un transporte nuevo obliga a modificar este if/else (viola OCP).
 *  - Se pierde el tipo real y hay que castear para llamar a entregar().
 */
public class LogisticaService {

    public String planificarEntrega(String tipoTransporte) {
        Object transporte;

        if (tipoTransporte.equals("tierra")) {
            transporte = new Camion();
        } else if (tipoTransporte.equals("mar")) {
            transporte = new Barco();
        } else {
            throw new IllegalArgumentException("Tipo de transporte desconocido: " + tipoTransporte);
        }

        // Camion y Barco no comparten tipo, asi que solo caben en un Object.
        // Al perder el tipo real hay que castear para llamar a entregar().
        if (transporte instanceof Camion) {
            return ((Camion) transporte).entregar();
        }
        return ((Barco) transporte).entregar();
    }
}
