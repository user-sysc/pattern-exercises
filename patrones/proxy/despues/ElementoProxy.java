package despues;

class ElementoProxy implements Documento {
    private DocumentoReal documentoReal;
    private String nombre;
    private boolean tienePermiso;

    public ElementoProxy(String nombre, boolean tienePermiso) {
        this.nombre = nombre;
        this.tienePermiso = tienePermiso;
    }

    public void Acceder() {
        if (tienePermiso) {
            System.out.println("Permiso verificado.");

            if (documentoReal == null) {
                documentoReal = new DocumentoReal(nombre);
            }

            documentoReal.Acceder();
            documentoReal.CargarImagen();
        } else {
            System.out.println("Acceso denegado.");
        }
    }

    public void CargarImagen() {
        if (!tienePermiso) {
            System.out.println("No autorizado para cargar la imagen.");
            return;
        }

        if (documentoReal == null) {
            documentoReal = new DocumentoReal(nombre);
        }

        documentoReal.CargarImagen();
    }
}
