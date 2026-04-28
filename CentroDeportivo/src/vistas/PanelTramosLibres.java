package vistas;

import javax.swing.*;
import centrodeportivo.modelo.*;

public class PanelTramosLibres extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CentroDeportivo centro = CentroDeportivo.getInstancia();

    public PanelTramosLibres() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JComboBox<Instalacion> combo =
                new JComboBox<>(centro.getInstalacionesOrdenadasPorId());

        UIUtil.estilizarCombo(combo);

        JTextField fecha = new JTextField();
        UIUtil.estilizarCampo(fecha);

        JTextArea area = new JTextArea();
		UIUtil.estilizarArea(area);
		
		
        add(combo);
        add(new JLabel("Fecha:"));
        add(fecha);

        JButton btn = new JButton("Buscar tramos libres");
        add(btn);
        
        JScrollPane scroll = new JScrollPane(area);
		UIUtil.estilizarScroll(scroll);
		add(scroll);

        btn.addActionListener(e -> {
            area.setText("");
            Instalacion i = (Instalacion) combo.getSelectedItem();

            String[] libres = centro.identificarTramosLibres(i, fecha.getText());

            for (String s : libres) {
                area.append(s + "\n");
            }
        });
    }
}
