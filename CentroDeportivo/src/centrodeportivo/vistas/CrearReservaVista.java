package centrodeportivo.vistas;

import java.awt.Font;
import javax.swing.*;
import centrodeportivo.controladores.CrearReservaControlador;

public class CrearReservaVista extends JPanel {
	private JTextField txtUsuario, txtIdIns, txtFecha, txtHora, txtExtra;
	private JComboBox<String> comboTipo;
	private JLabel lblExtra;
	private CrearReservaControlador controlador;

	public CrearReservaVista() {
		
		setLayout(null); 
		
		controlador = new CrearReservaControlador(this);

		JLabel lblTitulo = new JLabel("GESTIÓN DE RESERVAS");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setBounds(160, 20, 250, 20);
		add(lblTitulo);

		// Definimos los espacios donde el usuario escribirá la información obligatoria de la reserva
		
		add(new JLabel("Socio:")).setBounds(50, 60, 100, 20);
		txtUsuario = new JTextField();
		txtUsuario.setBounds(180, 60, 150, 20);
		add(txtUsuario);

		add(new JLabel("ID Instalación:")).setBounds(50, 90, 100, 20);
		txtIdIns = new JTextField();
		txtIdIns.setBounds(180, 90, 150, 20);
		add(txtIdIns);

		add(new JLabel("Tipo Reserva:")).setBounds(50, 120, 100, 20);
		String[] tipos = { "INDIVIDUAL", "GRUPAL", "ACTIVIDAD_DIRIGIDA" };
		comboTipo = new JComboBox<>(tipos);
		comboTipo.setBounds(180, 120, 150, 20);
		add(comboTipo);

		// Espacio para poner datos adicionales, como el número de personas o el nombre de la clase
		
		lblExtra = new JLabel("Dato extra:");
		lblExtra.setBounds(50, 150, 120, 20);
		lblExtra.setVisible(false);
		add(lblExtra);

		txtExtra = new JTextField();
		txtExtra.setBounds(180, 150, 150, 20);
		txtExtra.setVisible(false);
		add(txtExtra);

		add(new JLabel("Fecha:")).setBounds(50, 180, 100, 20);
		txtFecha = new JTextField();
		txtFecha.setBounds(180, 180, 150, 20);
		add(txtFecha);

		add(new JLabel("Hora:")).setBounds(50, 210, 100, 20);
		txtHora = new JTextField();
		txtHora.setBounds(180, 210, 150, 20);
		add(txtHora);

		// Controla que solo se vean las opciones de "Participantes" o "Actividad" cuando sea necesario
		
		comboTipo.addActionListener(e -> actualizarCampos());

		JButton btn = new JButton("Realizar Reserva");
		btn.setBounds(150, 260, 180, 30);
		btn.addActionListener(controlador);
		add(btn);
	}

	private void actualizarCampos() {
		String sel = (String) comboTipo.getSelectedItem();
		if (sel.equals("GRUPAL")) {
			lblExtra.setText("Nº Personas:");
			lblExtra.setVisible(true);
			txtExtra.setVisible(true);
		} else if (sel.equals("ACTIVIDAD_DIRIGIDA")) {
			lblExtra.setText("Actividad:");
			lblExtra.setVisible(true);
			txtExtra.setVisible(true);
		} else {
			lblExtra.setVisible(false);
			txtExtra.setVisible(false);
		}
	}

	// Getters para el controlador
	
	public String getUsuario() {
		return txtUsuario.getText();
	}

	public String getIdIns() {
		return txtIdIns.getText();
	}

	public String getTipo() {
		return (String) comboTipo.getSelectedItem();
	}

	public String getExtra() {
		return txtExtra.getText();
	}

	public String getFecha() {
		return txtFecha.getText();
	}

	public String getHora() {
		return txtHora.getText();
	}

	public void aviso(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
}