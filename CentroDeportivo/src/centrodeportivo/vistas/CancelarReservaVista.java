package centrodeportivo.vistas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import centrodeportivo.controladores.CancelarReservaControlador;

public class CancelarReservaVista extends JPanel {

	// Aquí guardamos el campo de texto donde el usuario ingesara el ID de la reserva que quiere cancelar
	
	private JTextField txtIdReserva;

	// Guardamos a nuestro controlador, que será el que hara practicamente la mayoria del trabajo
	
	private CancelarReservaControlador controlador;

	public CancelarReservaVista() {

		// Desactivamos el diseño automático para poder colocar los textos y botones a mano
		
		setLayout(null);

		// Creamos el controlador y le pasamos esta misma ventana con (this) para que puedan hablar
		
		controlador = new CancelarReservaControlador(this);

		// Este será el título de la pantalla
		
		JLabel lblTitulo = new JLabel("CANCELAR UNA RESERVA");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16)); // Lo ponemos un poco más grande y en negrita
		lblTitulo.setBounds(150, 20, 250, 20);
		add(lblTitulo);

		// Ponemos una pequeña etiqueta al lado para que el usuario sepa qué dato le estamos pidiendo
		
		JLabel lblId = new JLabel("ID de la Reserva:");
		lblId.setBounds(120, 80, 150, 20);
		add(lblId);

		// Establecemos el campo de texto donde el usuario va a escribir el ID
		
		txtIdReserva = new JTextField();
		txtIdReserva.setBounds(250, 80, 100, 20);
		add(txtIdReserva);

		// Y creamos el botón que el usuario pulsará para confirmar la cancelación
		
		JButton btnCancelar = new JButton("Cancelar Reserva");
		btnCancelar.setBounds(180, 140, 150, 30);

		// Le decimos al botón que cuando lo pulsen, avise a nuestro controlador
		
		btnCancelar.addActionListener(controlador);
		add(btnCancelar);
	}

	// Método para que el controlador pueda venir aquí y leer el texto que han escrito
	
	public String getIdReserva() {
		return txtIdReserva.getText();
	}

	// Este método lo utilizara el controlador para mostrar ventanas de aviso al usuario
	
	public void mostrarMensaje(String mensaje) {
		// Y este desplegara una ventana emergente en el centro de la pantalla con el mensaje
		JOptionPane.showMessageDialog(this, mensaje);
	}
}