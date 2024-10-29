// Archivo: src/ReservaExternaAdapter.java
public class ReservaExternaAdapter implements Reserva {
    private final ReservaExterna reservaExterna;

    public ReservaExternaAdapter(ReservaExterna reservaExterna) {
        this.reservaExterna = reservaExterna;
    }

    @Override
    public void reservar() {
        reservaExterna.hacerReserva(); // Adaptamos la llamada
    }
}
