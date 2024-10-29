// Archivo: src/ReservaDecorator.java
public abstract class ReservaDecorator implements Reserva {
    protected Reserva reservaDecorada;

    public ReservaDecorator(Reserva reservaDecorada) {
        this.reservaDecorada = reservaDecorada;
    }

    @Override
    public void reservar() {
        reservaDecorada.reservar();
    }
}
