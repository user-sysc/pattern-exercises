package pago;

public class PayPal extends MetodoPago {

    private String email;
    private double saldo;

    public PayPal(String email, double saldo) {
        super("PayPal");
        this.email = email;
        this.saldo = saldo;
    }

    @Override
    public boolean pagar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("[PayPal] Transferencia de $" + String.format("%.2f", monto)
                    + " desde " + email + " | Saldo restante: $" + String.format("%.2f", saldo));
            return true;
        }
        System.out.println("[PayPal] Pago rechazado: saldo insuficiente");
        return false;
    }
}