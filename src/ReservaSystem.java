// Archivo: src/ReservaSystem.java
public class ReservaSystem {
    // La única instancia de la clase
    private static ReservaSystem instancia;

    // Constructor privado para evitar la creación de instancias externas
    private ReservaSystem() {
        // Inicializar cualquier recurso necesario para el sistema
    }

    // Método para obtener la instancia única de ReservaSystem
    public static ReservaSystem getInstancia() {
        if (instancia == null) {
            instancia = new ReservaSystem();
        }
        return instancia;
    }

    // Método simulado para registrar una reserva en el sistema
    public void registrarReserva(String detalleReserva) {
        System.out.println("Reserva registrada en el sistema: " + detalleReserva);
    }
}
