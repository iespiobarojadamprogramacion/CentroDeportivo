package vistas;

import javax.swing.*;
import centrodeportivo.modelo.*;

public class PanelOcupacion extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CentroDeportivo centro = CentroDeportivo.getInstancia();

    public PanelOcupacion() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField fecha = new JTextField();
        UIUtil.estilizarCampo(fecha);
        
        JTextArea area = new JTextArea();
		UIUtil.estilizarArea(area);
		
		

        JButton dia = new JButton("Ocupación día");
        JButton semana = new JButton("Ocupación semana");

        add(new JLabel("Fecha o primera fecha:"));
        add(fecha);
        add(dia);
        add(semana);
        JScrollPane scroll = new JScrollPane(area);
		UIUtil.estilizarScroll(scroll);
		add(scroll);
        
        dia.addActionListener(e -> {
            area.setText("");
            for (String s : centro.consultarOcupacionDiaria(fecha.getText())) {
                area.append(s + "\n");
            }
        });

        semana.addActionListener(e -> {
            area.setText("");
            String[] fechas = new String[7];
            for (int i = 0; i < 7; i++) {
                fechas[i] = fecha.getText(); // simplificado (puedes mejorar luego)
            }
            for (String s : centro.consultarOcupacionSemanal(fechas)) {
                area.append(s + "\n");
            }
        });
    }
}
