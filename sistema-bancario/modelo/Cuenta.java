package modelo;

/**
 * CLASE ABSTRACTA CUENTA
 * Define la estructura base para todas las cuentas
 * Demuestra: Abstracción, Encapsulamiento
 */
public abstract class Cuenta {
    private String numero;
    private double saldo;
    private Cliente titular;
    
    public Cuenta(String numero, Cliente titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0;
    }
    
    // Método concreto
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("❌ El monto debe ser mayor a 0.");
            return;
        }
        saldo += monto;
        System.out.println("✓ Depósito realizado. Nuevo saldo: $" + 
                         String.format("%.2f", saldo));
    }
    
    // Método abstracto - cada tipo de cuenta lo implementa diferente
    public abstract void retirar(double monto);
    
    public void consultarSaldo() {
        System.out.println("Saldo de la cuenta " + numero + ": $" + 
                         String.format("%.2f", saldo));
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    protected void modificarSaldo(double cantidad) {
        saldo += cantidad;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public Cliente getTitular() {
        return titular;
    }
    
    public abstract String getTipoCuenta();
}