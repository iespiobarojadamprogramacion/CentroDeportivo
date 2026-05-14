package centrodeportivo.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import centrodeportivo.modelo.APICentroDeportivo;
import centrodeportivo.vistas.ConsultaOcupacionVista;
import utilidades.Util; 
import utilidades.FechaIncorrectaException; 

public class ConsultaOcupacionControlador implements ActionListener {

	private ConsultaOcupacionVista vista;
	private APICentroDeportivo api = APICentroDeportivo.getInstance();

	public ConsultaOcupacionControlador(ConsultaOcupacionVista vista) {
		this.vista = vista;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Lo primero que hacemos sera sacar el texto de la fecha que se escribió en la cajita
		
		String fecha = vista.getFecha();

		// Comprobamos que no le haya dado al botón dejando el hueco en blanco
		
		if (fecha == null || fecha.isBlank()) {
			vista.mostrarAviso("Por favor, escribe una fecha antes de consultar.");
			return;
		}

		// Y atraparemos aquí cualquier error en la fecha
		
		try {
			// Validamos que de verdad sea una fecha y no letras sueltas
			Util.validarFecha(fecha);

			// Si la fecha está perfecta, le pedimos al centro que nos calcule la ocupación
			String[] datos = api.consultarOcupacion(fecha);

			// Y mandamos esos datos a la pantalla para que el usuario los vea
			vista.mostrarResultados(datos);

		} catch (FechaIncorrectaException ex) {
			// Si escribió letras o el formato está mal, el código saltara aquí directamente
			vista.mostrarAviso("Error: La fecha debe tener formato numérico (DD/MM/AAAA).");
		}
	}
}