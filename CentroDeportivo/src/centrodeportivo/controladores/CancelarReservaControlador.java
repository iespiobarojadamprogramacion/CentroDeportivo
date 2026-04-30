package centrodeportivo.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import centrodeportivo.modelo.APICentroDeportivo;
import centrodeportivo.vistas.CancelarReservaVista;

public class CancelarReservaControlador implements ActionListener {

	private CancelarReservaVista vista;
	private APICentroDeportivo api = APICentroDeportivo.getInstance();
	
	public CancelarReservaControlador(CancelarReservaVista vista) {
		this.vista = vista;
	}

	// Este se ejecutara cuando pinchen en el botón
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Tomara el ID de la pantalla
		
		String id = vista.getIdReserva();
		
		// Si le dan al botón sin escribir nada, les avisaremos
		
		if (id.isBlank()) {
			vista.mostrarMensaje("Por favor, escribe un ID.");
			return; 
		}
		
		// Le mandamos el texto a la API y ella misma intenta pasarlo a número por nosotros
		
		boolean cancelada = api.cancelarReserva(id);
		
		// Avisamos al usuario del resultado
		
		if (cancelada) {
			vista.mostrarMensaje("¡Reserva cancelada con éxito!");
		} else {
			vista.mostrarMensaje("Error: No se encontró la reserva o escribiste letras.");
		}
	}
}