package vistas;

import javax.swing.*;
import centrodeportivo.modelo.*;

public class PanelModificarReserva extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CentroDeportivo centro = CentroDeportivo.getInstancia();

	public PanelModificarReserva() {

	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	    JTextField id = new JTextField();
	    UIUtil.estilizarCampo(id);
	    
	    JTextField fecha = new JTextField();
	    UIUtil.estilizarCampo(fecha);
	    
	    JTextField hora = new JTextField();
	    UIUtil.estilizarCampo(hora);
	    

	  
	    JComboBox<Instalacion> combo = 
	    		new JComboBox<>(centro.getInstalacionesOrdenadasPorId());
	    
	    UIUtil.estilizarCombo(combo);

	    JButton btn = new JButton("Modificar");

	    add(new JLabel("ID Reserva"));
	    add(id);

	    add(new JLabel("Nueva Fecha"));
	    add(fecha);

	    add(new JLabel("Nueva Hora"));
	    add(hora);

	    add(new JLabel("Nueva Instalación"));
	    add(combo);

	    add(btn);

	    btn.addActionListener(e -> {

	        Instalacion seleccionada = (Instalacion) combo.getSelectedItem();

	        boolean ok = centro.modificarReserva(
	                Integer.parseInt(id.getText()),
	                fecha.getText(),
	                hora.getText(),
	                seleccionada
	        );

	        JOptionPane.showMessageDialog(this,
	                ok ? "Modificada correctamente" : "Error al modificar");
	    });
	}
}
