import java.time.LocalDate;

public class ReservaCompleta {
    private final String cliente;
    private final LocalDate fecha;
    private final Reserva tipoReserva;

    // Constructor privado para recibir el Builder
    private ReservaCompleta(Builder builder) {
        this.cliente = builder.cliente;
        this.fecha = builder.fecha;
        this.tipoReserva = builder.tipoReserva;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Reserva getTipoReserva() {
        return tipoReserva;
    }

    @Override
    public String toString() {
        return "ReservaCompleta{" +
                "cliente='" + cliente + '\'' +
                ", fecha=" + fecha +
                ", tipoReserva=" + tipoReserva.getClass().getSimpleName() +
                '}';
    }

    // Clase interna Builder
    public static class Builder {
        private String cliente;
        private LocalDate fecha;
        private Reserva tipoReserva;

        public Builder cliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder tipoReserva(Reserva tipoReserva) {
            this.tipoReserva = tipoReserva;
            return this;
        }

        // Método para construir la instancia final de ReservaCompleta
        public ReservaCompleta build() {
            if (cliente == null || fecha == null || tipoReserva == null) {
                throw new IllegalStateException("Cliente, fecha y tipo de reserva son obligatorios");
            }
            return new ReservaCompleta(this);
        }
    }

    // Método para realizar pago, asegurado dentro de la clase ReservaCompleta
    public void realizarPago(PagoStrategy estrategiaPago, double monto) {
        estrategiaPago.pagar(monto);
    }
}
