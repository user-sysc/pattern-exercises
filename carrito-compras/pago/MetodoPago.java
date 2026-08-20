package pago;

public abstract class MetodoPago {

    private String nombre;

    public MetodoPago(String nombre) {
        this.nombre = nombre;
    }

    // Abstraccion: cada metodo de pago define su propia logica
    public abstract boolean pagar(double monto);

    public String getNombre() {
        return nombre;
    }
}