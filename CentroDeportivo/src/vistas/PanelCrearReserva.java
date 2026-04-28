package vistas;

import javax.swing.*;

import centrodeportivo.Ventana;
import centrodeportivo.modelo.*;

public class PanelCrearReserva extends JPanel {

    private static final long serialVersionUID = 1L;

    private CentroDeportivo centro = CentroDeportivo.getInstancia();

    public PanelCrearReserva() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField fecha = new JTextField();
        UIUtil.estilizarCampo(fecha);

        JTextField hora = new JTextField();
        UIUtil.estilizarCampo(hora);

        JComboBox<Instalacion> combo =
                new JComboBox<>(centro.getInstalacionesOrdenadasPorId());
        UIUtil.estilizarCombo(combo);

        JComboBox<String> comboTipo = new JComboBox<>(
                new String[]{"INDIVIDUAL", "GRUPAL", "ACTIVIDAD_DIRIGIDA"}
        );
        UIUtil.estilizarCombo(comboTipo);

        JTextField campoParticipantes = new JTextField();
        UIUtil.estilizarCampo(campoParticipantes);

        JTextField campoActividad = new JTextField();
        UIUtil.estilizarCampo(campoActividad);

        // ocultos al inicio
        campoParticipantes.setVisible(false);
        campoActividad.setVisible(false);

        add(new JLabel("Instalación"));
        add(combo);

        add(new JLabel("Fecha (dd/mm/aa)"));
        add(fecha);

        add(new JLabel("Hora (hh:mm)"));
        add(hora);

        add(new JLabel("Tipo de reserva"));
        add(comboTipo);

        add(new JLabel("Número de participantes"));
        add(campoParticipantes);

        add(new JLabel("Actividad dirigida"));
        add(campoActividad);

        JButton btn = new JButton("Crear reserva");
        add(btn);

        comboTipo.addActionListener(e -> {

            String tipo = (String) comboTipo.getSelectedItem();

            boolean esGrupal = tipo.equals("GRUPAL");
            boolean esDirigida = tipo.equals("ACTIVIDAD_DIRIGIDA");

            campoParticipantes.setVisible(esGrupal);
            campoActividad.setVisible(esDirigida);

            revalidate();
            repaint();
        });

        btn.addActionListener(e -> {

            Usuario u = Ventana.usuarioActual;

            if (u == null) {
                JOptionPane.showMessageDialog(this,
                        "Debes iniciar sesión primero");
                return;
            }

            Instalacion i = (Instalacion) combo.getSelectedItem();

            String fechaTxt = fecha.getText();
            String horaTxt = hora.getText();
            String tipo = (String) comboTipo.getSelectedItem();

            boolean ok = false;

         
            if (tipo.equals("INDIVIDUAL")) {

                ok = centro.crearReserva(
                        u,
                        i,
                        fechaTxt,
                        horaTxt,
                        "1h",
                        Estado_Reserva.ACTIVA,
                        null,
                        null,
                        1,
                        Tipo_Reserva.INDIVIDUAL
                );
            }

            else if (tipo.equals("GRUPAL")) {

                int num = Integer.parseInt(campoParticipantes.getText());

                ok = centro.crearReserva(
                        u,
                        i,
                        fechaTxt,
                        horaTxt,
                        "1h",
                        Estado_Reserva.ACTIVA,
                        null,
                        null,
                        num,
                        Tipo_Reserva.GRUPAL
                );
            }

            else if (tipo.equals("ACTIVIDAD_DIRIGIDA")) {

                String actividad = campoActividad.getText();

                ok = centro.crearReserva(
                        u,
                        i,
                        fechaTxt,
                        horaTxt,
                        "1h",
                        Estado_Reserva.ACTIVA,
                        "Monitor Asignado",
                        actividad,
                        1,
                        Tipo_Reserva.ACTIVIDAD_DIRIGIDA
                );
            }

            JOptionPane.showMessageDialog(this,
                    ok ? "Reserva creada correctamente"
                       : "No disponible o error");
        });
    }
}
