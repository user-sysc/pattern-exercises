package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CLASE HOTEL
 * Administra clientes, habitaciones y reservas
 * Demuestra: Composición, Asociación
 */
public class Hotel {
    private String nombre;
    private List<Cliente> clientes;
    private List<Habitacion> habitaciones;
    private List<Reserva> reservas;
    
    public Hotel(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }
    
    // Métodos de Cliente
    public void agregarCliente(Cliente cliente) {
        if (buscarClientePorDocumento(cliente.getDocumento()) != null) {
            System.out.println("❌ El cliente con documento " + 
                             cliente.getDocumento() + " ya existe.");
            return;
        }
        clientes.add(cliente);
        System.out.println("✓ Cliente " + cliente.getNombre() + 
                         " registrado correctamente.");
    }
    
    public Cliente buscarClientePorDocumento(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }
    
    // Métodos de Habitacion
    public void agregarHabitacion(Habitacion habitacion) {
        if (buscarHabitacion(habitacion.getNumero()) != null) {
            System.out.println("❌ La habitación " + 
                             habitacion.getNumero() + " ya existe.");
            return;
        }
        habitaciones.add(habitacion);
        System.out.println("✓ Habitación " + habitacion.getNumero() + 
                         " (" + habitacion.getTipo() + ") registrada.");
    }
    
    public Habitacion buscarHabitacion(int numero) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        return null;
    }
    
    public List<Habitacion> obtenerHabitacionesDisponibles() {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.isDisponible()) {
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }
    
    // Métodos de Reserva
    public Reserva hacerReserva(Cliente cliente, Habitacion habitacion, 
                               int noches, LocalDate fechaCheckIn) {
        if (!habitacion.isDisponible()) {
            System.out.println("❌ La habitación " + habitacion.getNumero() + 
                             " no está disponible.");
            return null;
        }
        
        Reserva reserva = new Reserva(cliente, habitacion, noches, fechaCheckIn);
        habitacion.reservar();
        reservas.add(reserva);
        return reserva;
    }
    
    public void cancelarReserva(int numeroReserva) {
        Reserva reserva = buscarReserva(numeroReserva);
        if (reserva == null) {
            System.out.println("❌ Reserva no encontrada.");
            return;
        }
        
        reserva.getHabitacion().liberar();
        reservas.remove(reserva);
        System.out.println("✓ Reserva #" + numeroReserva + " cancelada.");
    }
    
    public Reserva buscarReserva(int numeroReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroReserva() == numeroReserva) {
                return reserva;
            }
        }
        return null;
    }
    
    public void mostrarHabitaciones() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              HABITACIONES DEL HOTEL " + nombre);
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
            return;
        }
        
        for (Habitacion habitacion : habitaciones) {
            String estado = habitacion.isDisponible() 
                    ? "✓ Disponible" 
                    : "✗ " + habitacion.getEstado();
            System.out.println("\n🏠 " + habitacion.mostrarDetalles() + " [" + estado + "]");
        }
        System.out.println();
        lineaDivisoria();
    }
    
    public void mostrarReservas() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              RESERVAS DEL HOTEL " + nombre);
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        
        for (Reserva reserva : reservas) {
            System.out.println("\n📋 Reserva #" + reserva.getNumeroReserva() + 
                             " | Cliente: " + reserva.getCliente().getNombre() +
                             " | Habitación: " + reserva.getHabitacion().getNumero() +
                             " | Noches: " + reserva.getNoches() +
                             " | Total: $" + String.format("%.2f", reserva.calcularTotal()));
        }
        System.out.println();
        lineaDivisoria();
    }
    
    public void mostrarClientes() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              CLIENTES DEL HOTEL " + nombre);
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        
        for (Cliente cliente : clientes) {
            System.out.println("\n👤 " + cliente.getNombre() + 
                             " | Documento: " + cliente.getDocumento() +
                             " | Email: " + cliente.getEmail() +
                             " | Tel: " + cliente.getTelefono());
        }
        System.out.println();
        lineaDivisoria();
    }
    
    private void lineaDivisoria() {
        StringBuilder linea = new StringBuilder();
        for (int i = 0; i < 67; i++) {
            linea.append("═");
        }
        System.out.println(linea);
    }
    
    public String getNombre() {
        return nombre;
    }
}