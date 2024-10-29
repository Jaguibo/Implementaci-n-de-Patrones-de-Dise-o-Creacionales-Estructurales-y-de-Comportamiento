// Archivo: src/ServicioExtraDecorator.java
public class ServicioExtraDecorator extends ReservaDecorator {
    public ServicioExtraDecorator(Reserva reservaDecorada) {
        super(reservaDecorada);
    }

    @Override
    public void reservar() {
        reservaDecorada.reservar();
        agregarServicioExtra();
    }

    private void agregarServicioExtra() {
        System.out.println("Servicio extra añadido a la reserva.");
    }
}
