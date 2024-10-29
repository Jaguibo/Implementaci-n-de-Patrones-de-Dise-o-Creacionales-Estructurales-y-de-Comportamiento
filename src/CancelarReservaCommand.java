// Archivo: src/CancelarReservaCommand.java
public class CancelarReservaCommand implements ReservaCommand {
    private ReservaCompleta reserva;

    public CancelarReservaCommand(ReservaCompleta reserva) {
        this.reserva = reserva;
    }

    @Override
    public void ejecutar() {
        System.out.println("Cancelando reserva:");
        // Aquí puedes añadir la lógica de cancelación real
    }
}
