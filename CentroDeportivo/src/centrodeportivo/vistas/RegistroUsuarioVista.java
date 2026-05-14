package centrodeportivo.vistas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import centrodeportivo.controladores.RegistroUsuarioControlador;

public class RegistroUsuarioVista extends JPanel {

	// Variables para las cajas de texto donde escribirá el usuario
	
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtContrasena;

	// El controlador que manejará los clics de esta pantalla
	
	private RegistroUsuarioControlador controlador;

	public RegistroUsuarioVista() {
	
		setLayout(null); 
		
		controlador = new RegistroUsuarioControlador(this);

		// Título de la pantalla
		
		JLabel lblTitulo = new JLabel("REGISTRO DE NUEVO SOCIO");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setBounds(150, 20, 300, 20);
		add(lblTitulo);

		// Etiqueta y caja de texto para el nombre
		
		JLabel lblNombre = new JLabel("Nombre Completo:");
		lblNombre.setBounds(80, 80, 150, 20);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(220, 80, 180, 20);
		add(txtNombre);

		// Etiqueta y caja de texto para el teléfono
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(140, 120, 80, 20);
		add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(220, 120, 180, 20);
		add(txtTelefono);

		// Etiqueta y caja de texto para la contraseña
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(125, 160, 100, 20);
		add(lblContrasena);

		txtContrasena = new JTextField();
		txtContrasena.setBounds(220, 160, 180, 20);
		add(txtContrasena);

		// Botón para registrar
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(220, 220, 120, 30);
		// Le decimos al botón que avise al controlador cuando lo pulsen
		btnRegistrar.addActionListener(controlador);
		add(btnRegistrar);
	}

	// Métodos para que el controlador pueda leer lo que han escrito

	public String getNombre() {
		return txtNombre.getText();
	}

	public String getTelefono() {
		return txtTelefono.getText();
	}

	public String getContrasena() {
		return txtContrasena.getText();
	}

	// Y estos otros métodos serán para mostrar ventanitas de aviso

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarExito(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
}