package vistas;

import javax.swing.*;
import centrodeportivo.modelo.*;

public class PanelCancelarReserva extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CentroDeportivo centro = CentroDeportivo.getInstancia();

    public PanelCancelarReserva() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField id = new JTextField();
        UIUtil.estilizarCampo(id);

        JButton btn = new JButton("Cancelar reserva");

        add(new JLabel("ID Reserva"));
        add(id);
        add(btn);

        btn.addActionListener(e -> {
            boolean ok = centro.cancelarReserva(Integer.parseInt(id.getText()));
            JOptionPane.showMessageDialog(this,
                    ok ? "Cancelada" : "No encontrada");
        });
    }
}
