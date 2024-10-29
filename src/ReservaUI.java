// Archivo: src/ReservaUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReservaUI extends JFrame {
    private JComboBox<String> tipoReservaCombo;
    private JCheckBox seguroCheckBox;
    private JCheckBox servicioExtraCheckBox;
    private JTextField clienteField;
    private JTextField fechaField;
    private JComboBox<String> metodoPagoCombo;
    private JTextArea notificacionesArea;

    public ReservaUI() {
        setTitle("Sistema de Reservas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel panelDatos = new JPanel(new GridLayout(6, 2));

        // Tipo de reserva
        panelDatos.add(new JLabel("Tipo de Reserva:"));
        tipoReservaCombo = new JComboBox<>(new String[]{"hotel", "vuelo", "auto"});
        panelDatos.add(tipoReservaCombo);

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
        btnCancelar.addActionListener(e -> notificacionesArea.append("Reserva cancelada.\n"));
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
            reservaCompleta.realizarPago(estrategiaPago, 100.0); // monto ficticio
            notificacionesArea.append("Reserva registrada para " + cliente + " en " + fecha + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReservaUI ui = new ReservaUI();
            ui.setVisible(true);
        });
    }
}
