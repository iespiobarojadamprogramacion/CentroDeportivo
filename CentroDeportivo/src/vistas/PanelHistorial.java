package vistas;

import javax.swing.*;
import centrodeportivo.Ventana;
import centrodeportivo.modelo.*;

public class PanelHistorial extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CentroDeportivo centro = CentroDeportivo.getInstancia();

    public PanelHistorial() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextArea area = new JTextArea();
        UIUtil.estilizarArea(area);

        JButton btn = new JButton("Ver historial");

        add(btn);

        btn.addActionListener(e -> {
            area.setText("");

            Usuario u = Ventana.usuarioActual;

            if (u == null) {
                area.setText("No hay usuario logueado");
                return;
            }

            for (Reserva r : u.consultarHistorialUso()) {
                area.append(r.getIdReserva() + " - " + r.getFecha() + "\n");
            }

        });
    }
}
