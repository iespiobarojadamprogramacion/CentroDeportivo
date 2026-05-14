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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		//  Extraeremos el texto que el usuario escribió en la casilla del ID
		
		String idTexto = vista.getIdReserva();

		// Comprobamos que no le haya dado al botón dejando el espacio en blanco
		
		if (idTexto == null || idTexto.isBlank()) {
			vista.mostrarMensaje("Por favor, escribe un ID de reserva.");
			return; // Y con este return paramos todo para que el programa no siga leyendo hacia abajo
		}

		// Haremos también la conversión a número y atraparemos los errores 
		
		try {
			// Intentaremos convertir el texto a un número entero
			int idNumero = Integer.valueOf(idTexto);

			// Si la conversión sale bien, le pasaremos el número limpio a la API
			boolean cancelada = api.cancelarReserva(idNumero);

			// Comprobamos si la API nos dice que lo logró cancelar o si no existía
			if (cancelada) {
				vista.mostrarMensaje("¡Reserva cancelada con éxito!");
			} else {
				vista.mostrarMensaje("No se ha encontrado ninguna reserva activa con ese ID.");
			}

		} catch (NumberFormatException nfe) {
			/* Y si el usuario escribió letras en vez de un número, el Integer.valueOf fallará.
			Por lo que en ese caso, el código saltara directamente aquí, atrapando el error sin que el
			programa falle*/
			vista.mostrarMensaje("Error: El ID de la reserva tiene que ser solo números.");
		}
	}
}