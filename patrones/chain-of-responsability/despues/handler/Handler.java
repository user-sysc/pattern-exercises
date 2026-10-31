package despues.handler;

import despues.domain.Request;

/**
 * Clase base abstracta de la cadena de responsabilidad.
 *
 * Define la referencia al siguiente manejador (next), el método setNext para encadenar
 * de manera fluida, la plantilla de ejecución handle(), y el método abstracto procesar()
 * que cada manejador concreto debe implementar.
 */
public abstract class Handler {

    protected Handler next;

    public Handler setNext(Handler next) {
        this.next = next;
        return next; // permite encadenar: a.setNext(b).setNext(c)
    }

    public void handle(Request req) {
        if (procesar(req) && next != null) {
            next.handle(req);
        }
    }

    // true = continúa la cadena; false = la corta aquí
    protected abstract boolean procesar(Request req);
}
