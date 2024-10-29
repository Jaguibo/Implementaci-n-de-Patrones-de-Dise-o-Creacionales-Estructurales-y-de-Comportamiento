// Archivo: src/HacerReservaCommand.java
public class HacerReservaCommand implements ReservaCommand {
    private ReservaCompleta reserva;

    public HacerReservaCommand(ReservaCompleta reserva) {
        this.reserva = reserva;
    }

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando reserva:");
        reserva.getTipoReserva().reservar();
    }
}
