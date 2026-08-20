package modelo;

import java.time.LocalDate;

/**
 * CLASE RESERVA
 * Demuestra: Composición (contiene Cliente y Habitacion)
 */
public class Reserva {
    private static int contador = 1000;
    private int numeroReserva;
    private Cliente cliente;
    private Habitacion habitacion;
    private int noches;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    
    public Reserva(Cliente cliente, Habitacion habitacion, int noches, LocalDate fechaCheckIn) {
        this.numeroReserva = ++contador;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.noches = noches;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckIn.plusDays(noches);
    }
    
    public double calcularTotal() {
        return habitacion.getPrecioPorNoche() * noches;
    }
    
    public int getNumeroReserva() {
        return numeroReserva;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public Habitacion getHabitacion() {
        return habitacion;
    }
    
    public int getNoches() {
        return noches;
    }
    
    public LocalDate getFechaCheckIn() {
        return fechaCheckIn;
    }
    
    public LocalDate getFechaCheckOut() {
        return fechaCheckOut;
    }
    
    public void mostrarReserva() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║                    RESERVA                      ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("📋 Número de Reserva: #" + numeroReserva);
        System.out.println("👤 Cliente: " + cliente.getNombre());
        System.out.println("🏠 Habitación: " + habitacion.getNumero() + 
                         " (" + habitacion.getTipo() + ")");
        System.out.println("💰 Precio por noche: $" + 
                         String.format("%.2f", habitacion.getPrecioPorNoche()));
        System.out.println("📅 Noches: " + noches);
        System.out.println("📍 Check-in: " + fechaCheckIn);
        System.out.println("📍 Check-out: " + fechaCheckOut);
        lineaDivisoria();
        System.out.println("💵 TOTAL: $" + String.format("%.2f", calcularTotal()));
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }
    
    private void lineaDivisoria() {
        StringBuilder linea = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            linea.append("─");
        }
        System.out.println(linea);
    }
}