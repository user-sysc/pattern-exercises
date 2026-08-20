import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Alimento;
import modelo.Carrito;
import modelo.Electronico;
import modelo.Producto;
import modelo.Ropa;
import pago.Efectivo;
import pago.MetodoPago;
import pago.PayPal;
import pago.TarjetaCredito;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Producto> catalogo = new ArrayList<>();
        catalogo.add(new Alimento("A001", "Manzana", 2.0));
        catalogo.add(new Alimento("A002", "Leche", 1.5));
        catalogo.add(new Electronico("E001", "Laptop", 900.0));
        catalogo.add(new Electronico("E002", "Auriculares", 80.0));
        catalogo.add(new Electronico("E003", "Monitor 4K", 400.0));
        catalogo.add(new Ropa("R001", "Camiseta", 20.0));
        catalogo.add(new Ropa("R002", "Jeans", 45.0));

        Carrito carrito = new Carrito();
        int opcion;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Ver catalogo de productos");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Eliminar producto del carrito");
            System.out.println("4. Ver carrito");
            System.out.println("5. Procesar pago");
            System.out.println("6. Vaciar carrito");
            System.out.println("7. Salir");
            System.out.print("Elige una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    mostrarCatalogo(catalogo);
                    break;
                case 2:
                    agregarAlCarrito(carrito, catalogo);
                    break;
                case 3:
                    eliminarDelCarrito(carrito);
                    break;
                case 4:
                    carrito.mostrarResumen();
                    break;
                case 5:
                    procesarPago(carrito);
                    break;
                case 6:
                    carrito.vaciar();
                    System.out.println("Carrito vaciado.");
                    break;
                case 7:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 7);
    }

    private static void mostrarCatalogo(List<Producto> catalogo) {
        System.out.println("===== CATALOGO DE PRODUCTOS =====");
        for (Producto producto : catalogo) {
            System.out.println("  " + producto.getCodigo() + " - " + producto);
        }
    }

    private static void agregarAlCarrito(Carrito carrito, List<Producto> catalogo) {
        System.out.print("Codigo del producto: ");
        String codigo = scanner.nextLine();

        Producto producto = buscarProducto(catalogo, codigo);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = leerEntero();

        carrito.agregar(producto, cantidad);
        System.out.println("Producto agregado al carrito.");
    }

    private static Producto buscarProducto(List<Producto> catalogo, String codigo) {
        for (Producto producto : catalogo) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                return producto;
            }
        }
        return null;
    }

    private static void eliminarDelCarrito(Carrito carrito) {
        System.out.print("Codigo del producto a eliminar: ");
        String codigo = scanner.nextLine();

        carrito.eliminar(codigo);
        System.out.println("Producto eliminado (si existia en el carrito).");
    }

    private static void procesarPago(Carrito carrito) {
        if (carrito.getItems().isEmpty()) {
            System.out.println("El carrito esta vacio, agrega productos primero.");
            return;
        }

        carrito.mostrarResumen();
        System.out.println("\n===== METODO DE PAGO =====");
        System.out.println("1. Tarjeta de credito");
        System.out.println("2. Efectivo");
        System.out.println("3. PayPal");
        System.out.print("Elige el metodo de pago: ");

        int opcion = leerEntero();
        MetodoPago metodo = null;

        switch (opcion) {
            case 1:
                System.out.print("Numero de tarjeta: ");
                String numero = scanner.nextLine();
                System.out.print("Limite disponible: ");
                double limite = leerDecimal();
                metodo = new TarjetaCredito(numero, limite);
                break;
            case 2:
                System.out.print("Dinero entregado: ");
                double dinero = leerDecimal();
                metodo = new Efectivo(dinero);
                break;
            case 3:
                System.out.print("Email de PayPal: ");
                String email = scanner.nextLine();
                System.out.print("Saldo disponible: ");
                double saldo = leerDecimal();
                metodo = new PayPal(email, saldo);
                break;
            default:
                System.out.println("Metodo de pago no valido.");
                return;
        }

        carrito.procesarPago(metodo);
    }

    private static int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double leerDecimal() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}