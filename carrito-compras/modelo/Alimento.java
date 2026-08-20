package modelo;

public class Alimento extends Producto {

    private static final double IVA = 0.04;

    public Alimento(String codigo, String nombre, double precioBase) {
        super(codigo, nombre, precioBase);
    }

    @Override
    public double getImpuesto() {
        return IVA;
    }
}