// Archivo: src/PagoTarjetaCredito.java
public class PagoTarjetaCredito implements PagoStrategy {
    private String numeroTarjeta;

    public PagoTarjetaCredito(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pago de $" + monto + " realizado con tarjeta de crédito: " + numeroTarjeta);
    }
}
