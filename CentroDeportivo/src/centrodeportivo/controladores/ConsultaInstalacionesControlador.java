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

	// Esto saltara cuando el usuario de clic en "Cargar Instalaciones"
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Recogera todos los datos de las instalaciones a través de nuestra API
		
		String[][] resultado = api.consultarInstalaciones();

		// Y se los pasamos a la ventana para que los dibuje en la tabla
		
		vista.setDatosTabla(resultado);
	}
}