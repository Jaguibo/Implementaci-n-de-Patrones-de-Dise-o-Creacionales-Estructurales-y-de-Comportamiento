// Archivo: src/InvokerReserva.java
public class InvokerReserva {
    private ReservaCommand comando;

    public void setComando(ReservaCommand comando) {
        this.comando = comando;
    }

    public void ejecutarComando() {
        comando.ejecutar();
    }
}
