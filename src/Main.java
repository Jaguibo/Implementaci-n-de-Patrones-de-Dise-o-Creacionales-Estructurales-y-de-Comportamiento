// Archivo: src/Main.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

public class Main extends JFrame {
    private JComboBox<String> tipoReservaCombo;
    private JLabel precioLabel;
    private JCheckBox seguroCheckBox;
    private JCheckBox servicioExtraCheckBox;
    private JTextField clienteField;
    private JTextField fechaField;
    private JComboBox<String> metodoPagoCombo;
    private JTextArea notificacionesArea;
    private ReservaObservable reservaObservable;
    private Usuario usuarioNotificacion;

    public Main() {
        setTitle("Sistema de Reservas");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        reservaObservable = new ReservaObservable();
        usuarioNotificacion = new Usuario("Usuario"); // Usuario que recibirá notificaciones
        reservaObservable.agregarObservador(usuarioNotificacion);

        // Panel de entrada de datos
        JPanel panelDatos = new JPanel(new GridLayout(8, 2));

        // Tipo de reserva
        panelDatos.add(new JLabel("Tipo de Reserva:"));
        tipoReservaCombo = new JComboBox<>(new String[]{"hotel", "vuelo", "auto"});
        tipoReservaCombo.addItemListener(new TipoReservaListener()); // Listener para actualizar precio
        panelDatos.add(tipoReservaCombo);

        // Mostrar el precio de la reserva
        panelDatos.add(new JLabel("Precio de la Reserva:"));
        precioLabel = new JLabel("$" + ReservaFactory.obtenerPrecioReserva((String) tipoReservaCombo.getSelectedItem()));
        panelDatos.add(precioLabel);

        // Cliente
        panelDatos.add(new JLabel("Cliente:"));
        clienteField = new JTextField();
        panelDatos.add(clienteField);

        // Fecha
        panelDatos.add(new JLabel("Fecha (yyyy-mm-dd):"));
        fechaField = new JTextField();
        panelDatos.add(fechaField);

        // Opciones de personalización
        seguroCheckBox = new JCheckBox("Agregar Seguro");
        servicioExtraCheckBox = new JCheckBox("Agregar Servicio Extra");
        panelDatos.add(seguroCheckBox);
        panelDatos.add(servicioExtraCheckBox);

        // Método de pago
        panelDatos.add(new JLabel("Método de Pago:"));
        metodoPagoCombo = new JComboBox<>(new String[]{"Tarjeta de Crédito", "PayPal"});
        panelDatos.add(metodoPagoCombo);

        add(panelDatos, BorderLayout.CENTER);

        // Botones de acción
        JPanel panelBotones = new JPanel();
        JButton btnRegistrar = new JButton("Registrar Reserva");
        JButton btnCancelar = new JButton("Cancelar Reserva");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Área de notificaciones
        notificacionesArea = new JTextArea(5, 30);
        notificacionesArea.setEditable(false);
        add(new JScrollPane(notificacionesArea), BorderLayout.NORTH);

        // Eventos de botones
        btnRegistrar.addActionListener(new RegistrarReservaAction());
        btnCancelar.addActionListener(e -> {
            reservaObservable.cambiarEstado("Cancelada");
            notificacionesArea.append("Reserva cancelada.\n");
        });
    }

    // Listener para actualizar el precio cuando se selecciona un nuevo tipo de reserva
    private class TipoReservaListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String tipoSeleccionado = (String) tipoReservaCombo.getSelectedItem();
                double precio = ReservaFactory.obtenerPrecioReserva(tipoSeleccionado);
                precioLabel.setText("$" + precio);
            }
        }
    }

    // Acción para registrar la reserva
    private class RegistrarReservaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tipoReserva = (String) tipoReservaCombo.getSelectedItem();
            String cliente = clienteField.getText();
            LocalDate fecha;
            try {
                fecha = LocalDate.parse(fechaField.getText());
            } catch (Exception ex) {
                notificacionesArea.append("Fecha inválida. Use el formato yyyy-mm-dd.\n");
                return;
            }

            // Crear la reserva usando Factory y Builder
            Reserva reserva = ReservaFactory.crearReserva(tipoReserva);
            if (seguroCheckBox.isSelected()) {
                reserva = new SeguroDecorator(reserva);
            }
            if (servicioExtraCheckBox.isSelected()) {
                reserva = new ServicioExtraDecorator(reserva);
            }
            ReservaCompleta reservaCompleta = new ReservaCompleta.Builder()
                    .cliente(cliente)
                    .fecha(fecha)
                    .tipoReserva(reserva)
                    .build();

            // Elegir el método de pago usando Strategy
            String metodoPago = (String) metodoPagoCombo.getSelectedItem();
            PagoStrategy estrategiaPago;
            if ("Tarjeta de Crédito".equals(metodoPago)) {
                estrategiaPago = new PagoTarjetaCredito("1234-5678-9012-3456"); // Número ficticio
            } else {
                estrategiaPago = new PagoPayPal("cliente@email.com"); // Email ficticio
            }

            // Realizar el pago y registrar la reserva
            double precio = ReservaFactory.obtenerPrecioReserva(tipoReserva); // Obtener precio para el pago
            reservaCompleta.realizarPago(estrategiaPago, precio);
            notificacionesArea.append("Reserva registrada para " + cliente + " en " + fecha + "\n");

            // Notificar cambio de estado
            reservaObservable.cambiarEstado("Confirmada");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ui = new Main();
            ui.setVisible(true);
        });
    }
}
