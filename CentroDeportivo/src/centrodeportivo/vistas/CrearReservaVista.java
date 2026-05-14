package centrodeportivo.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import centrodeportivo.controladores.CrearReservaControlador;

public class CrearReservaVista extends JPanel {

	private JTextField txtUsuario, txtIdIns, txtFecha, txtHora, txtExtra;
	private JComboBox<String> comboTipo;
	private JLabel lblExtra;
	
	
	private CrearReservaControlador controlador;

	public CrearReservaVista() {

		setLayout(null);

		controlador = new CrearReservaControlador(this);

		// Aquí creamos el título de la parte superior para que se sepa que estamos en Reservas
		
		JLabel lblTitulo = new JLabel("GESTIÓN DE RESERVAS");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setBounds(160, 20, 250, 20);
		add(lblTitulo);

		// Definimos primero los campos de texto que son indispensables para cualquier reserva
		
		JLabel lblUsuario = new JLabel("Socio:");
		lblUsuario.setBounds(50, 60, 100, 20);
		add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(180, 60, 150, 20);
		add(txtUsuario);

		JLabel lblIdIns = new JLabel("ID Instalación:");
		lblIdIns.setBounds(50, 90, 100, 20);
		add(lblIdIns);

		txtIdIns = new JTextField();
		txtIdIns.setBounds(180, 90, 150, 20);
		add(txtIdIns);

		JLabel lblTipo = new JLabel("Tipo Reserva:");
		lblTipo.setBounds(50, 120, 100, 20);
		add(lblTipo);

		// Creamos el desplegable con las 3 opciones exactas del Enum que tenemos en el modelo
		
		String[] tipos = { "INDIVIDUAL", "GRUPAL", "ACTIVIDAD_DIRIGIDA" };
		comboTipo = new JComboBox<>(tipos);
		comboTipo.setBounds(180, 120, 150, 20);
		add(comboTipo);

		// Dejamos este espacio oculto al principio, ya que solo lo mostraremos si es una reserva Grupal o Dirigida
		
		lblExtra = new JLabel("Dato extra:");
		lblExtra.setBounds(50, 150, 120, 20);
		lblExtra.setVisible(false); // Empieza oculto 
		add(lblExtra);

		txtExtra = new JTextField();
		txtExtra.setBounds(180, 150, 150, 20);
		txtExtra.setVisible(false); 
		add(txtExtra);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(50, 180, 100, 20);
		add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(180, 180, 150, 20);
		add(txtFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(50, 210, 100, 20);
		add(lblHora);

		txtHora = new JTextField();
		txtHora.setBounds(180, 210, 150, 20);
		add(txtHora);

		// Aquí empezaremos a configurar los escuchadores para que la ventana reaccione cuando el usuario toque algo

		// Le ponemos un escuchador al desplegable para que cambie la pantalla al seleccionar algo distinto
		
		comboTipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarCampos(); // Llamamos al método visual de abajo
			}
		});

		// Y este es el botón final que avisa al controlador de que ya puede intentar hacer la reserva
		
		JButton btn = new JButton("Realizar Reserva");
		btn.setBounds(150, 260, 180, 30);
		btn.addActionListener(controlador);
		add(btn);
	}

	// Tambień por otro lado controlaremos qué casillas se muestran o se esconden para que la interfaz sea más limpia
	
	private void actualizarCampos() {
		String sel = (String) comboTipo.getSelectedItem();

		if (sel.equals("GRUPAL")) {
			// Si es grupal, pedimos el número de participantes
			lblExtra.setText("Nº Personas:");
			lblExtra.setVisible(true);
			txtExtra.setVisible(true);

		} else if (sel.equals("ACTIVIDAD_DIRIGIDA")) {
			// Si es dirigida, pedimos el nombre de la clase
			lblExtra.setText("Actividad:");
			lblExtra.setVisible(true);
			txtExtra.setVisible(true);

		} else {
			// Y si es individual, volvemos a esconder las casillas porque no hacen falta
			lblExtra.setVisible(false);
			txtExtra.setVisible(false);
		}
	}


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

	// Función que mostrara alertas en la pantalla y mantendra informado al usuario de lo que ocurre
	
	public void aviso(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
}