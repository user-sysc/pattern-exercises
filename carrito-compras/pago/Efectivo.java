package pago;

public class Efectivo extends MetodoPago {

    private double dineroEntregado;

    public Efectivo(double dineroEntregado) {
        super("Efectivo");
        this.dineroEntregado = dineroEntregado;
    }

    @Override
    public boolean pagar(double monto) {
        if (dineroEntregado >= monto) {
            double vuelto = dineroEntregado - monto;
            System.out.println("[Efectivo] Pago recibido de $" + String.format("%.2f", monto)
                    + " | Vuelto: $" + String.format("%.2f", vuelto));
            return true;
        }
        System.out.println("[Efectivo] Pago rechazado: dinero insuficiente");
        return false;
    }
}