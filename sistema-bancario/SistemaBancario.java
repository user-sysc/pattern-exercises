import java.util.Scanner;
import modelo.Banco;
import modelo.Cliente;
import modelo.Cuenta;
import modelo.CuentaAhorros;
import modelo.CuentaCorriente;

/**
 * CLASE PRINCIPAL CON MENÚ INTERACTIVO
 */
public class SistemaBancario {
    private static Banco banco;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        banco = new Banco("Banco POO");
        scanner = new Scanner(System.in);
        
        mostrarBienvenida();
        
        boolean ejecutar = true;
        while (ejecutar) {
            mostrarMenu();
            int opcion = obtenerOpcion();
            
            switch (opcion) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    crearCuenta();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    realizarRetiro();
                    break;
                case 5:
                    consultarSaldo();
                    break;
                case 6:
                    transferirDinero();
                    break;
                case 7:
                    banco.mostrarClientes();
                    break;
                case 8:
                    banco.mostrarCuentas();
                    break;
                case 9:
                    despedirse();
                    ejecutar = false;
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarBienvenida() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    BIENVENIDO AL BANCO POO                      ║");
        System.out.println("║                                                                ║");
        System.out.println("║  Sistema de demostración de POO:                               ║");
        System.out.println("║  • Herencia                                                    ║");
        System.out.println("║  • Polimorfismo                                                ║");
        System.out.println("║  • Abstracción                                                 ║");
        System.out.println("║  • Encapsulamiento                                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
    }
    
    private static void mostrarMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       MENÚ PRINCIPAL                            ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Crear un nuevo cliente                                      ║");
        System.out.println("║ 2. Crear una nueva cuenta                                      ║");
        System.out.println("║ 3. Realizar un depósito                                        ║");
        System.out.println("║ 4. Realizar un retiro                                          ║");
        System.out.println("║ 5. Consultar saldo                                             ║");
        System.out.println("║ 6. Transferir dinero entre cuentas                             ║");
        System.out.println("║ 7. Ver todos los clientes                                      ║");
        System.out.println("║ 8. Ver todas las cuentas                                       ║");
        System.out.println("║ 9. Salir                                                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }
    
    private static int obtenerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
    
    private static void crearCliente() {
        System.out.println("\n╔═ CREAR NUEVO CLIENTE ═╗");
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Documento de identidad: ");
        String documento = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Cliente nuevoCliente = new Cliente(nombre, documento, email);
        banco.agregarCliente(nuevoCliente);
    }
    
    private static void crearCuenta() {
        System.out.println("\n╔═ CREAR NUEVA CUENTA ═╗");
        System.out.print("Documento del cliente: ");
        String documento = scanner.nextLine();
        
        Cliente cliente = banco.buscarCliente(documento);
        if (cliente == null) {
            System.out.println("❌ Cliente no encontrado.");
            return;
        }
        
        System.out.println("\n¿Qué tipo de cuenta desea crear?");
        System.out.println("1. Cuenta de Ahorros");
        System.out.println("2. Cuenta Corriente");
        System.out.print("Seleccione una opción: ");
        int tipo = obtenerOpcion();
        
        String numeroCuenta = banco.generarNumeroCuenta();
        
        if (tipo == 1) {
            System.out.print("Límite de retiro ($): ");
            double limite = scanner.nextDouble();
            scanner.nextLine();
            
            Cuenta cuenta = new CuentaAhorros(numeroCuenta, cliente, limite);
            banco.agregarCuenta(cuenta);
        } else if (tipo == 2) {
            System.out.print("Límite de sobregiro ($): ");
            double limite = scanner.nextDouble();
            scanner.nextLine();
            
            Cuenta cuenta = new CuentaCorriente(numeroCuenta, cliente, limite);
            banco.agregarCuenta(cuenta);
        } else {
            System.out.println("❌ Opción no válida.");
        }
    }
    
    private static void realizarDeposito() {
        System.out.println("\n╔═ REALIZAR DEPÓSITO ═╗");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        Cuenta cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            System.out.println("❌ Cuenta no encontrada.");
            return;
        }
        
        System.out.print("Monto a depositar ($): ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        
        cuenta.depositar(monto);
    }
    
    private static void realizarRetiro() {
        System.out.println("\n╔═ REALIZAR RETIRO ═╗");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        Cuenta cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            System.out.println("❌ Cuenta no encontrada.");
            return;
        }
        
        System.out.print("Monto a retirar ($): ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        
        cuenta.retirar(monto);
    }
    
    private static void consultarSaldo() {
        System.out.println("\n╔═ CONSULTAR SALDO ═╗");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        Cuenta cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            System.out.println("❌ Cuenta no encontrada.");
            return;
        }
        
        System.out.println("\n📊 Información de la Cuenta:");
        System.out.println("Número: " + cuenta.getNumero());
        System.out.println("Tipo: " + cuenta.getTipoCuenta());
        System.out.println("Titular: " + cuenta.getTitular().getNombre());
        cuenta.consultarSaldo();
    }
    
    private static void transferirDinero() {
        System.out.println("\n╔═ TRANSFERENCIA ENTRE CUENTAS ═╗");
        System.out.print("Número de cuenta origen: ");
        String cuentaOrigen = scanner.nextLine();
        System.out.print("Número de cuenta destino: ");
        String cuentaDestino = scanner.nextLine();
        System.out.print("Monto a transferir ($): ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        
        Cuenta origen = banco.buscarCuenta(cuentaOrigen);
        Cuenta destino = banco.buscarCuenta(cuentaDestino);
        
        if (origen == null || destino == null) {
            System.out.println("❌ Una o ambas cuentas no existen.");
            return;
        }
        
        System.out.println("\n💸 Procesando transferencia...");
        double saldoAntes = origen.getSaldo();
        origen.retirar(monto);
        
        // Solo se deposita si el retiro realmente se realizó (el saldo cambió)
        if (origen.getSaldo() != saldoAntes) {
            destino.depositar(monto);
            System.out.println("✓ Transferencia completada.");
        } else {
            System.out.println("❌ Transferencia cancelada: el retiro no se pudo realizar.");
        }
    }
    
    private static void despedirse() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║   Gracias por usar el Banco POO.                               ║");
        System.out.println("║   ¡Hasta pronto!                                               ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
    }
}