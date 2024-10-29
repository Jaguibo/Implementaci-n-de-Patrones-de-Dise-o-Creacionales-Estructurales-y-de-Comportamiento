// Archivo: src/ReservaFacade.java
import java.time.LocalDate;

public class ReservaFacade {
    public void reservarHotel(String cliente, LocalDate fecha) {
        Reserva reserva = ReservaFactory.crearReserva("hotel");
        ReservaCompleta reservaCompleta = new ReservaCompleta.Builder()
                .cliente(cliente)
                .fecha(fecha)
                .tipoReserva(reserva)
                .build();
        System.out.println(reservaCompleta);
    }

    public void reservarVuelo(String cliente, LocalDate fecha) {
        Reserva reserva = ReservaFactory.crearReserva("vuelo");
        ReservaCompleta reservaCompleta = new ReservaCompleta.Builder()
                .cliente(cliente)
                .fecha(fecha)
                .tipoReserva(reserva)
                .build();
        System.out.println(reservaCompleta);
    }

    public void reservarAuto(String cliente, LocalDate fecha) {
        Reserva reserva = ReservaFactory.crearReserva("auto");
        ReservaCompleta reservaCompleta = new ReservaCompleta.Builder()
                .cliente(cliente)
                .fecha(fecha)
                .tipoReserva(reserva)
                .build();
        System.out.println(reservaCompleta);
    }

    public void reservarExterna(String cliente, LocalDate fecha) {
        ReservaExterna reservaExterna = new ReservaExterna();
        Reserva reservaAdaptada = new ReservaExternaAdapter(reservaExterna);
        ReservaCompleta reservaCompleta = new ReservaCompleta.Builder()
                .cliente(cliente)
                .fecha(fecha)
                .tipoReserva(reservaAdaptada)
                .build();
        System.out.println(reservaCompleta);
    }
}
