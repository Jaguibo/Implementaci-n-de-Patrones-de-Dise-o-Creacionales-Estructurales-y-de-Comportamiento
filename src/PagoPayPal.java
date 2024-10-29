// Archivo: src/PagoPayPal.java
public class PagoPayPal implements PagoStrategy {
    private String email;

    public PagoPayPal(String email) {
        this.email = email;
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pago de $" + monto + " realizado con PayPal: " + email);
    }
}
