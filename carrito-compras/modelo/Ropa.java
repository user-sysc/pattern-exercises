package modelo;

public class Ropa extends Producto {

    private static final double IVA = 0.10;

    public Ropa(String codigo, String nombre, double precioBase) {
        super(codigo, nombre, precioBase);
    }

    @Override
    public double getImpuesto() {
        return IVA;
    }
}