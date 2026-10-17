package despues;

class DocumentoReal implements Documento {
    private String nombre;

    public DocumentoReal(String nombre) {
        this.nombre = nombre;
    }

    public void Acceder() {
        System.out.println("Abriendo documento: " + nombre);
    }

    public void CargarImagen() {
        System.out.println("Cargando imagen pesada...");
    }
}
