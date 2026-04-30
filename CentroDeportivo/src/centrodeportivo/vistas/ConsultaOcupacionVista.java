package centrodeportivo.vistas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import centrodeportivo.controladores.ConsultaOcupacionControlador;

public class ConsultaOcupacionVista extends JPanel {

	private JTextField txtFecha;

	// Preparamos un recuadro amplio para que quepan todas las respuestas
	
	private JTextArea areaResultado;

	private ConsultaOcupacionControlador controlador;

	public ConsultaOcupacionVista() {
		
		setLayout(null);

		controlador = new ConsultaOcupacionControlador(this);

		// Título principal
		
		JLabel lblTitulo = new JLabel("CONSULTAR OCUPACIÓN DIARIA");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setBounds(130, 20, 300, 20);
		add(lblTitulo);

		// Texto y caja para que ingresen la fecha
		
		JLabel lblFecha = new JLabel("Fecha (ej: 04/04/2026):");
		lblFecha.setBounds(60, 70, 160, 20);
		add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(220, 70, 100, 20);
		add(txtFecha);

		// Botón para buscar
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(340, 65, 120, 30);
		btnConsultar.addActionListener(controlador);
		add(btnConsultar);

		// Preparamos la zona donde saldrán los resultados
		
		areaResultado = new JTextArea();
		// Lo bloqueamos para que el usuario no pueda borrar el texto
		areaResultado.setEditable(false);

		// Le ponemos las barras de desplazamiento 
		
		JScrollPane scroll = new JScrollPane(areaResultado);
		scroll.setBounds(80, 120, 400, 150);
		add(scroll);
	}

	// Método para que el controlador pida la fecha escrita
	
	public String getFecha() {
		return txtFecha.getText();
	}

	// Este cogera los datos que hemos encontrado y los escribira uno por uno en la pantalla
	
	public void mostrarResultados(String[] resultados) {
		// Primero borramos lo que hubiera de antes
		areaResultado.setText("");

		// Y ahora vamos escribiendo línea por línea lo que nos manda el controlador
		for (String linea : resultados) {
			areaResultado.append(linea + "\n");
		}
	}

	// Ventanita de aviso por si se olvidan de escribir la fecha
	
	public void mostrarAviso(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
}