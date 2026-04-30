package centrodeportivo.vistas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import centrodeportivo.controladores.ConsultaInstalacionesControlador;

public class ConsultaInstalacionesVista extends JPanel {

	private JTable tablaInstalaciones;
	private JScrollPane scrollTabla;

	// Preparamos los títulos de las columnas de nuestra tabla
	
	private final String[] cabeceraTabla = { "ID", "Nombre", "Tipo" };

	// Empezamos sin datos para que la tabla aparezca vacía al principio
	
	private String[][] datosVacios = new String[0][3];

	private ConsultaInstalacionesControlador controlador;

	public ConsultaInstalacionesVista() {
		
		setLayout(null);

		controlador = new ConsultaInstalacionesControlador(this);

		// Título de la pantalla
		
		JLabel lblTitulo = new JLabel("INSTALACIONES DEL CENTRO");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setBounds(150, 20, 280, 20);
		add(lblTitulo);

		// Botón para pedirle a la API que cargue los datos
		
		JButton btnCargar = new JButton("Cargar Instalaciones");
		btnCargar.setBounds(180, 60, 180, 30);
		btnCargar.addActionListener(controlador);
		add(btnCargar);

		// Creamos la tabla y le ponemos las líneas negras 
		
		tablaInstalaciones = new JTable(datosVacios, cabeceraTabla);
		tablaInstalaciones.setGridColor(Color.BLACK);

		// Metemos la tabla en un ScrollPane (para que tenga barra de desplazamiento si hay muchas)
		
		scrollTabla = new JScrollPane(tablaInstalaciones);
		scrollTabla.setBounds(50, 120, 450, 200);
		add(scrollTabla);
	}

	// Este método lo usará el controlador para meter los datos a la tabla
	
	public void setDatosTabla(String[][] datos) {
		tablaInstalaciones.setModel(new DefaultTableModel(datos, cabeceraTabla));
	}
}