package modelo;

public class Electronico extends Producto {

    private static final double IVA = 0.21;

    public Electronico(String codigo, String nombre, double precioBase) {
        super(codigo, nombre, precioBase);
    }

    @Override
    public double getImpuesto() {
        return IVA;
    }
}