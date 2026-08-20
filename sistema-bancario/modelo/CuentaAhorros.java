package modelo;

/**
 * CLASE CUENTA AHORROS
 * Hereda de Cuenta
 * Demuestra: Herencia, Polimorfismo
 */
public class CuentaAhorros extends Cuenta {
    private double limiteRetiro;
    
    public CuentaAhorros(String numero, Cliente titular, double limiteRetiro) {
        super(numero, titular);
        this.limiteRetiro = limiteRetiro;
    }
    
    @Override
    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("❌ El monto debe ser mayor a 0.");
            return;
        }
        
        if (monto > limiteRetiro) {
            System.out.println("❌ No puede retirar más de $" + 
                             String.format("%.2f", limiteRetiro));
            return;
        }
        
        if (monto > getSaldo()) {
            System.out.println("❌ Saldo insuficiente. Saldo actual: $" + 
                             String.format("%.2f", getSaldo()));
            return;
        }
        
        modificarSaldo(-monto);
        System.out.println("✓ Retiro realizado. Nuevo saldo: $" + 
                         String.format("%.2f", getSaldo()));
    }
    
    @Override
    public String getTipoCuenta() {
        return "Cuenta de Ahorros";
    }
    
    public double getLimiteRetiro() {
        return limiteRetiro;
    }
}