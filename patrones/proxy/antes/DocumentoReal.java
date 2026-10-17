package antes;

class DocumentoReal {
    private String nombre;
    private boolean tienePermiso;

    public DocumentoReal(String nombre, boolean tienePermiso) {
        this.nombre = nombre;
        this.tienePermiso = tienePermiso;

        // Problema: carga la imagen inmediatamente
        CargarImagen();
    }

    public void Acceder() {
        if (tienePermiso) {
            System.out.println("Acceso permitido a: " + nombre);
        } else {
            System.out.println("Acceso denegado");
        }
    }

    public void CargarImagen() {
        System.out.println("Cargando imagen pesada...");
    }
}
