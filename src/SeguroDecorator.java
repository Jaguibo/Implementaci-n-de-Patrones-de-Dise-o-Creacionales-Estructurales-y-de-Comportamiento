// Archivo: src/SeguroDecorator.java
public class SeguroDecorator extends ReservaDecorator {
    public SeguroDecorator(Reserva reservaDecorada) {
        super(reservaDecorada);
    }

    @Override
    public void reservar() {
        reservaDecorada.reservar();
        agregarSeguro();
    }

    private void agregarSeguro() {
        System.out.println("Seguro añadido a la reserva.");
    }
}
