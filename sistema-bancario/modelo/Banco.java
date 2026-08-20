package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASE BANCO
 * Administra clientes y cuentas
 * Demuestra: Composición, Asociación
 */
public class Banco {
    private String nombre;
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;
    private int proximoNumeroCuenta;
    
    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
        this.proximoNumeroCuenta = 1001;
    }
    
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("✓ Cliente " + cliente.getNombre() + " registrado correctamente.");
    }
    
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        System.out.println("✓ Cuenta " + cuenta.getNumero() + 
                         " (" + cuenta.getTipoCuenta() + ") registrada correctamente.");
    }
    
    public Cliente buscarCliente(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }
    
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumero().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
    
    public List<Cuenta> obtenerCuentasDelCliente(Cliente cliente) {
        List<Cuenta> cuentasCliente = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getTitular().getDocumento().equals(cliente.getDocumento())) {
                cuentasCliente.add(cuenta);
            }
        }
        return cuentasCliente;
    }
    
    public String generarNumeroCuenta() {
        return String.valueOf(proximoNumeroCuenta++);
    }
    
    public void mostrarCuentas() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              CUENTAS DEL BANCO " + nombre);
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
            return;
        }
        
        for (Cuenta cuenta : cuentas) {
            System.out.println("\n📊 Cuenta: " + cuenta.getNumero());
            System.out.println("   Tipo: " + cuenta.getTipoCuenta());
            System.out.println("   Titular: " + cuenta.getTitular().getNombre());
            System.out.println("   Saldo: $" + String.format("%.2f", cuenta.getSaldo()));
            
            if (cuenta instanceof CuentaAhorros) {
                System.out.println("   Límite de Retiro: $" + 
                                 String.format("%.2f", ((CuentaAhorros)cuenta).getLimiteRetiro()));
            } else if (cuenta instanceof CuentaCorriente) {
                System.out.println("   Límite de Sobregiro: $" + 
                                 String.format("%.2f", ((CuentaCorriente)cuenta).getLimiteSobregiro()));
            }
        }
        System.out.println();
        lineaDivisoria();
    }
    
    public void mostrarClientes() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║           CLIENTES DEL BANCO " + nombre);
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        
        for (Cliente cliente : clientes) {
            System.out.println("\n👤 " + cliente.getNombre());
            System.out.println("   Documento: " + cliente.getDocumento());
            System.out.println("   Email: " + cliente.getEmail());
            System.out.println("   Cuentas: " + obtenerCuentasDelCliente(cliente).size());
        }
        System.out.println();
        lineaDivisoria();
    }
    
    private void lineaDivisoria() {
        StringBuilder linea = new StringBuilder();
        for (int i = 0; i < 65; i++) {
            linea.append("─");
        }
        System.out.println(linea);
    }
    
    public String getNombre() {
        return nombre;
    }
}