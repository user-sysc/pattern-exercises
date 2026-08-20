package modelo;

/**
 * CLASE CUENTA CORRIENTE
 * Hereda de Cuenta
 * Demuestra: Herencia, Polimorfismo
 */
public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro;
    
    public CuentaCorriente(String numero, Cliente titular, double limiteSobregiro) {
        super(numero, titular);
        this.limiteSobregiro = limiteSobregiro;
    }
    
    @Override
    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("❌ El monto debe ser mayor a 0.");
            return;
        }
        
        double saldoDespuesDelRetiro = getSaldo() - monto;
        
        if (saldoDespuesDelRetiro < -limiteSobregiro) {
            System.out.println("❌ Se excedió el límite de sobregiro de $" + 
                             String.format("%.2f", limiteSobregiro));
            System.out.println("   Saldo disponible (incluido sobregiro): $" + 
                             String.format("%.2f", getSaldo() + limiteSobregiro));
            return;
        }
        
        modificarSaldo(-monto);
        System.out.println("✓ Retiro realizado. Nuevo saldo: $" + 
                         String.format("%.2f", getSaldo()));
    }
    
    @Override
    public String getTipoCuenta() {
        return "Cuenta Corriente";
    }
    
    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }
}