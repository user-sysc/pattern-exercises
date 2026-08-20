import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import modelo.Cliente;
import modelo.Habitacion;
import modelo.HabitacionDoble;
import modelo.HabitacionIndividual;
import modelo.Hotel;
import modelo.Reserva;
import modelo.Suite;

/**
 * CLASE PRINCIPAL CON MENÚ INTERACTIVO
 */
public class SistemaReservaHabitaciones {
    private static Hotel hotel;
    private static Scanner scanner;
    private static DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static void main(String[] args) {
        hotel = new Hotel("Hotel POO Luxury");
        scanner = new Scanner(System.in);
        
        mostrarBienvenida();
        inicializarDatos();
        
        boolean ejecutar = true;
        while (ejecutar) {
            mostrarMenu();
            int opcion = obtenerOpcion();
            
            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    registrarHabitacion();
                    break;
                case 3:
                    hacerReserva();
                    break;
                case 4:
                    cancelarReserva();
                    break;
                case 5:
                    realizarCheckIn();
                    break;
                case 6:
                    realizarCheckOut();
                    break;
                case 7:
                    consultarDisponibilidad();
                    break;
                case 8:
                    hotel.mostrarHabitaciones();
                    break;
                case 9:
                    hotel.mostrarReservas();
                    break;
                case 10:
                    hotel.mostrarClientes();
                    break;
                case 11:
                    despedirse();
                    ejecutar = false;
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarBienvenida() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  HOTEL POO LUXURY - SISTEMA DE RESERVAS          ║");
        System.out.println("║                                                                ║");
        System.out.println("║  Demostración de conceptos POO:                                ║");
        System.out.println("║  • Encapsulamiento                                             ║");
        System.out.println("║  • Abstracción                                                 ║");
        System.out.println("║  • Herencia                                                    ║");
        System.out.println("║  • Polimorfismo                                                ║");
        System.out.println("║  • Composición                                                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
    }
    
    private static void mostrarMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       MENÚ PRINCIPAL                            ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ 1.  Registrar cliente                                          ║");
        System.out.println("║ 2.  Registrar habitación                                       ║");
        System.out.println("║ 3.  Hacer una reserva                                          ║");
        System.out.println("║ 4.  Cancelar reserva                                           ║");
        System.out.println("║ 5.  Check-in (ocupar habitación)                               ║");
        System.out.println("║ 6.  Check-out (liberar habitación)                             ║");
        System.out.println("║ 7.  Consultar disponibilidad                                   ║");
        System.out.println("║ 8.  Ver todas las habitaciones                                 ║");
        System.out.println("║ 9.  Ver todas las reservas                                     ║");
        System.out.println("║ 10. Ver todos los clientes                                     ║");
        System.out.println("║ 11. Salir                                                      ║");
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
    
    private static void registrarCliente() {
        System.out.println("\n╔═ REGISTRAR CLIENTE ═╗");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Documento: ");
        String documento = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        Cliente cliente = new Cliente(nombre, documento, email, telefono);
        hotel.agregarCliente(cliente);
    }
    
    private static void registrarHabitacion() {
        System.out.println("\n╔═ REGISTRAR HABITACIÓN ═╗");
        System.out.print("Número de habitación: ");
        int numero = scanner.nextInt();
        System.out.print("Precio por noche ($): ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("\nTipo de habitación:");
        System.out.println("1. Individual");
        System.out.println("2. Doble");
        System.out.println("3. Suite");
        System.out.print("Seleccione: ");
        int tipo = obtenerOpcion();
        
        Habitacion habitacion = null;
        
        switch (tipo) {
            case 1:
                habitacion = new HabitacionIndividual(numero, precio);
                break;
            case 2:
                habitacion = new HabitacionDoble(numero, precio);
                break;
            case 3:
                habitacion = new Suite(numero, precio);
                break;
            default:
                System.out.println("❌ Opción no válida.");
                return;
        }
        
        hotel.agregarHabitacion(habitacion);
    }
    
    private static void hacerReserva() {
        System.out.println("\n╔═ HACER RESERVA ═╗");
        System.out.print("Documento del cliente: ");
        String documento = scanner.nextLine();
        
        Cliente cliente = hotel.buscarClientePorDocumento(documento);
        if (cliente == null) {
            System.out.println("❌ Cliente no encontrado.");
            return;
        }
        
        System.out.print("Número de habitación: ");
        int numeroHabitacion = scanner.nextInt();
        scanner.nextLine();
        
        Habitacion habitacion = hotel.buscarHabitacion(numeroHabitacion);
        if (habitacion == null) {
            System.out.println("❌ Habitación no encontrada.");
            return;
        }
        
        System.out.print("Número de noches: ");
        int noches = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Fecha de check-in (yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine();
        
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, formato);
        } catch (Exception e) {
            System.out.println("❌ Fecha inválida. Use el formato yyyy-MM-dd.");
            return;
        }
        
        Reserva reserva = hotel.hacerReserva(cliente, habitacion, noches, fecha);
        if (reserva != null) {
            reserva.mostrarReserva();
        }
    }
    
    private static void cancelarReserva() {
        System.out.println("\n╔═ CANCELAR RESERVA ═╗");
        System.out.print("Número de reserva: ");
        int numeroReserva = scanner.nextInt();
        scanner.nextLine();
        
        hotel.cancelarReserva(numeroReserva);
    }
    
    private static void realizarCheckIn() {
        System.out.println("\n╔═ CHECK-IN ═╗");
        System.out.print("Número de reserva: ");
        int numeroReserva = scanner.nextInt();
        scanner.nextLine();
        
        Reserva reserva = hotel.buscarReserva(numeroReserva);
        if (reserva == null) {
            System.out.println("❌ Reserva no encontrada.");
            return;
        }
        
        reserva.getHabitacion().ocupar();
        System.out.println("✓ Check-in realizado para " + reserva.getCliente().getNombre());
    }
    
    private static void realizarCheckOut() {
        System.out.println("\n╔═ CHECK-OUT ═╗");
        System.out.print("Número de habitación: ");
        int numeroHabitacion = scanner.nextInt();
        scanner.nextLine();
        
        Habitacion habitacion = hotel.buscarHabitacion(numeroHabitacion);
        if (habitacion == null) {
            System.out.println("❌ Habitación no encontrada.");
            return;
        }
        
        habitacion.liberar();
        System.out.println("✓ Check-out realizado. Habitación disponible.");
    }
    
    private static void consultarDisponibilidad() {
        System.out.println("\n╔═ HABITACIONES DISPONIBLES ═╗");
        List<Habitacion> disponibles = hotel.obtenerHabitacionesDisponibles();
        
        if (disponibles.isEmpty()) {
            System.out.println("No hay habitaciones disponibles.");
            return;
        }
        
        System.out.println("\n" + disponibles.size() + " habitación(es) disponible(s):\n");
        for (Habitacion habitacion : disponibles) {
            System.out.println("🏠 " + habitacion.mostrarDetalles());
        }
    }
    
    private static void despedirse() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║   Gracias por usar Hotel POO Luxury.                            ║");
        System.out.println("║   ¡Que disfrute su estadía!                                     ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
    }
    
    private static void inicializarDatos() {
        // Clientes de prueba
        Cliente c1 = new Cliente("Julio Rodríguez", "123456789", "julio@email.com", "3105551234");
        Cliente c2 = new Cliente("María García", "987654321", "maria@email.com", "3105555678");
        Cliente c3 = new Cliente("Carlos López", "456789123", "carlos@email.com", "3105559999");
        
        hotel.agregarCliente(c1);
        hotel.agregarCliente(c2);
        hotel.agregarCliente(c3);
        
        // Habitaciones de prueba
        Habitacion h1 = new HabitacionIndividual(101, 80000);
        Habitacion h2 = new HabitacionIndividual(102, 80000);
        Habitacion h3 = new HabitacionDoble(201, 150000);
        Habitacion h4 = new HabitacionDoble(202, 150000);
        Habitacion h5 = new Suite(301, 300000);
        Habitacion h6 = new Suite(302, 300000);
        
        hotel.agregarHabitacion(h1);
        hotel.agregarHabitacion(h2);
        hotel.agregarHabitacion(h3);
        hotel.agregarHabitacion(h4);
        hotel.agregarHabitacion(h5);
        hotel.agregarHabitacion(h6);
        
        System.out.println("\n✓ Sistema inicializado con datos de prueba.\n");
    }
}