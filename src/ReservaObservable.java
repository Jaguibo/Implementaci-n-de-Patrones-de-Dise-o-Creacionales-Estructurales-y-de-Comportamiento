// Archivo: src/ReservaObservable.java
import java.util.ArrayList;
import java.util.List;

public class ReservaObservable {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(mensaje);
        }
    }

    // Método para simular el cambio de estado de la reserva
    public void cambiarEstado(String nuevoEstado) {
        System.out.println("El estado de la reserva ha cambiado a: " + nuevoEstado);
        notificarObservadores("El estado de su reserva ahora es: " + nuevoEstado);
    }
}
