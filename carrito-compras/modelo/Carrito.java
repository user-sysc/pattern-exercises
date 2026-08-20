package modelo;

import java.util.ArrayList;
import java.util.List;
import pago.MetodoPago;

public class Carrito {

    // Composicion: el carrito se compone de items
    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregar(Producto producto, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodigo().equals(producto.getCodigo())) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminar(String codigo) {
        items.removeIf(item -> item.getProducto().getCodigo().equals(codigo));
    }

    public void vaciar() {
        items.clear();
    }

    public double getTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    // Polimorfismo: recibe cualquier subclase de MetodoPago
    public void procesarPago(MetodoPago metodo) {
        double total = getTotal();
        if (metodo.pagar(total)) {
            System.out.println("Pago exitoso de $" + String.format("%.2f", total)
                    + " con " + metodo.getNombre());
            vaciar();
        } else {
            System.out.println("Pago rechazado con " + metodo.getNombre());
        }
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void mostrarResumen() {
        System.out.println("===== RESUMEN DEL CARRITO =====");
        for (ItemCarrito item : items) {
            System.out.println("  " + item);
        }
        System.out.println("TOTAL: $" + String.format("%.2f", getTotal()));
    }
}