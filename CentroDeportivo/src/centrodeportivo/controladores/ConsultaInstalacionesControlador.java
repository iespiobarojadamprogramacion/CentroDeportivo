package centrodeportivo.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import centrodeportivo.modelo.APICentroDeportivo;
import centrodeportivo.vistas.ConsultaInstalacionesVista;

public class ConsultaInstalacionesControlador implements ActionListener {

	private ConsultaInstalacionesVista vista;
	private APICentroDeportivo api = APICentroDeportivo.getInstance();

	public ConsultaInstalacionesControlador(ConsultaInstalacionesVista vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Ponemos este mensaje en la consola solo para comprobar por debajo que el botón funciona bien
		
		System.out.println("Cargando lista de instalaciones...");

		// Llamamos a la API para que nos dé los datos y poder rellenar la tabla
		
		String[][] resultado = api.consultarInstalaciones();

		// Y por último, le pasamos esta información a la ventana para que la muestre en la tabla
		
		vista.setDatosTabla(resultado);
	}
}