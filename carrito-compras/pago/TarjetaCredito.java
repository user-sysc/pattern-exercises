package pago;

public class TarjetaCredito extends MetodoPago {

    private String numero;
    private double limiteDisponible;

    public TarjetaCredito(String numero, double limiteDisponible) {
        super("Tarjeta de credito");
        this.numero = numero;
        this.limiteDisponible = limiteDisponible;
    }

    @Override
    public boolean pagar(double monto) {
        if (monto <= limiteDisponible) {
            limiteDisponible -= monto;
            System.out.println("[TarjetaCredito] Pago aprobado de $" + String.format("%.2f", monto)
                    + " | Limite restante: $" + String.format("%.2f", limiteDisponible));
            return true;
        }
        System.out.println("[TarjetaCredito] Pago rechazado: limite insuficiente");
        return false;
    }
}