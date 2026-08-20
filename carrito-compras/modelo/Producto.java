package modelo;

public abstract class Producto {

    private String codigo;
    private String nombre;
    private double precioBase;

    public Producto(String codigo, String nombre, double precioBase) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    // Abstraccion: cada tipo de producto define su propio impuesto
    public abstract double getImpuesto();

    public double calcularPrecioFinal() {
        return precioBase + precioBase * getImpuesto();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public String toString() {
        return nombre + " (precio final: $" + String.format("%.2f", calcularPrecioFinal()) + ")";
    }
}