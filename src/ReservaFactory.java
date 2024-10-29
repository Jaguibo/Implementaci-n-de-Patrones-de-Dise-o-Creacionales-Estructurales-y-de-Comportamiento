// Archivo: src/ReservaFactory.java
public class ReservaFactory {
    public static Reserva crearReserva(String tipo) {
        switch (tipo.toLowerCase()) {
            case "hotel":
                return new ReservaHotel();
            case "vuelo":
                return new ReservaVuelo();
            case "auto":
                return new ReservaAuto();
            default:
                throw new IllegalArgumentException("Tipo de reserva desconocido: " + tipo);
        }
    }

    // Método para obtener el precio según el tipo de reserva
    public static double obtenerPrecioReserva(String tipo) {
        switch (tipo.toLowerCase()) {
            case "hotel":
                return 150.0; // Precio ficticio para un hotel
            case "vuelo":
                return 300.0; // Precio ficticio para un vuelo
            case "auto":
                return 75.0;  // Precio ficticio para alquiler de auto
            default:
                throw new IllegalArgumentException("Tipo de reserva desconocido: " + tipo);
        }
    }
}
