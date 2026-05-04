package centrodeportivo.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import centrodeportivo.modelo.APICentroDeportivo;
import centrodeportivo.vistas.ConsultaOcupacionVista;

public class ConsultaOcupacionControlador implements ActionListener {

	private ConsultaOcupacionVista vista;
	private APICentroDeportivo api = APICentroDeportivo.getInstance();

	public ConsultaOcupacionControlador(ConsultaOcupacionVista vista) {
		this.vista = vista;
	}

	// Cuando pulsen el botón de "Consultar"
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Se cogerá la fecha de la cajita
		
		String fecha = vista.getFecha();

		// Comprobaremos que no esté vacía
		
		if (fecha.isBlank()) {
			vista.mostrarAviso("Por favor, escribe una fecha antes de consultar.");
			return;
		}

		// Le pediremos al centro que nos calcule la ocupación de ese día
		
		String[] datos = api.consultarOcupacion(fecha);

		// Y mandaremos esos datos a la pantalla para que se vean
		
		vista.mostrarResultados(datos);
	}
}