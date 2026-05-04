package centrodeportivo.vistas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import centrodeportivo.controladores.CancelarReservaControlador;

public class CancelarReservaVista extends JPanel {

	// Solo necesitamos una cajita para que escriban el número
	
	private JTextField txtIdReserva;
	private CancelarReservaControlador controlador;

	public CancelarReservaVista() {
		
		setLayout(null);

		// Lo conectamos con nuestro controlador
		
		controlador = new CancelarReservaControlador(this);

		// Título
		
		JLabel lblTitulo = new JLabel("CANCELAR UNA RESERVA");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setBounds(150, 20, 250, 20);
		add(lblTitulo);

		// Texto indicativo
		
		JLabel lblId = new JLabel("ID de la Reserva:");
		lblId.setBounds(120, 80, 150, 20);
		add(lblId);

		// La cajita de texto
		
		txtIdReserva = new JTextField();
		txtIdReserva.setBounds(250, 80, 100, 20);
		add(txtIdReserva);

		// Botón de cancelar
		
		JButton btnCancelar = new JButton("Cancelar Reserva");
		btnCancelar.setBounds(180, 140, 150, 30);
		btnCancelar.addActionListener(controlador);
		add(btnCancelar);
	}

	// Método para que el controlador pueda leer el número que han escrito
	
	public String getIdReserva() {
		return txtIdReserva.getText();
	}

	// Ventanita genérica para avisar de las cosas
	
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
}