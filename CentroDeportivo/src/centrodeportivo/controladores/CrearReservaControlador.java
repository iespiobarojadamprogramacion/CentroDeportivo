package centrodeportivo.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import centrodeportivo.modelo.APICentroDeportivo;
import centrodeportivo.vistas.CrearReservaVista;

public class CrearReservaControlador implements ActionListener {
	private CrearReservaVista vista;
	private APICentroDeportivo api = APICentroDeportivo.getInstance();

	public CrearReservaControlador(CrearReservaVista vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String user = vista.getUsuario();
			int id = Integer.parseInt(vista.getIdIns());
			String tipo = vista.getTipo();
			String fecha = vista.getFecha();
			String hora = vista.getHora();

			int numPart = 1;
			String actividad = "";

			// Guardamos los detalles específicos según el tipo de reserva seleccionado (participantes o nombre de la actividad)
			
			if (tipo.equals("GRUPAL")) {
				numPart = Integer.parseInt(vista.getExtra());
			} else if (tipo.equals("ACTIVIDAD_DIRIGIDA")) {
				actividad = vista.getExtra();
			}

			boolean ok = api.crearReservaCompleta(user, id, fecha, hora, tipo, numPart, actividad);

			if (ok) {
				vista.aviso("Reserva " + tipo + " creada correctamente.");
			} else {
				vista.aviso("No se pudo crear. Revisa disponibilidad o usuario.");
			}
		} catch (Exception ex) {
			vista.aviso("Error: Revisa que el ID y el número de personas sean correctos.");
		}
	}
}