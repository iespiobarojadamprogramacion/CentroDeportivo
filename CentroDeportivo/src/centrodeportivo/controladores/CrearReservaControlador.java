package centrodeportivo.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import centrodeportivo.modelo.APICentroDeportivo;
import centrodeportivo.modelo.Tipo_Reserva; 
import centrodeportivo.vistas.CrearReservaVista;
import utilidades.Util; 
import utilidades.FechaIncorrectaException;

public class CrearReservaControlador implements ActionListener {

	private CrearReservaVista vista;
	private APICentroDeportivo api = APICentroDeportivo.getInstance();

	public CrearReservaControlador(CrearReservaVista vista) {
		this.vista = vista;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Extraeremos todos los textos que el usuario escribió en la ventana
		
		String usuario = vista.getUsuario();
		String idTexto = vista.getIdIns();
		String tipoTexto = vista.getTipo();
		String fecha = vista.getFecha();
		String hora = vista.getHora();
		String extra = vista.getExtra(); // Recogemos el dato de esta casilla extra, que dependiendo de la reserva será un número o un texto

		// Comprobamos de igual forma que no se haya olvidado de rellenar lo más importante
		
		if (usuario.isBlank() || idTexto.isBlank() || fecha.isBlank() || hora.isBlank()) {
			vista.aviso("Por favor, rellena todos los campos principales antes de guardar.");
			return; 
		}

		// Validamos que la hora contenga al menos un número recorriendo el texto para evitar formatos incorrectos
		
		boolean tieneNumero = false;
		for (int i = 0; i < hora.length(); i++) {
			if (Character.isDigit(hora.charAt(i))) {
				tieneNumero = true;
				break;
			}
		}

		if (!tieneNumero) {
			vista.aviso("Error: El formato de hora no es válido. Debe contener números (ej: 10:00).");
			return;
		}

		// Y luego empezaremos con las conversiones y a atrapar los errores
		
		try {
			// Comprobamos que la fecha esté bien escrita
			Util.validarFecha(fecha);

			// Convertimos el ID de la instalación a número (si meten letras, saltará el error abajo)
			int id = Integer.valueOf(idTexto);
			
			// Validamos que el ID de la instalación esté entre el 1 y el 6 
			if (id < 1 || id > 6) {
				vista.aviso("Error: El ID de la instalación debe estar entre 1 y 6.");
				return;
			}

			// Convertimos el texto exacto al Enumerado 
			Tipo_Reserva tipoEnum = Tipo_Reserva.valueOf(tipoTexto);

			int numPart = 1;
			String actividad = "";

			// Revisamos qué tipo de reserva eligió para ver qué hacemos con el campo extra
			
			if (tipoEnum == Tipo_Reserva.GRUPAL) {
				// Si es grupal, el extra tiene que ser un número obligatoriamente
				numPart = Integer.valueOf(extra);
				
				// Validamos que el número de participantes esté dentro del rango permitido (2 a 6)
				if (numPart < 2 || numPart > 6) {
					vista.aviso("Error: Para reservas grupales el mínimo son 2 personas y el máximo 6.");
					return;
				}
				
			} else if (tipoEnum == Tipo_Reserva.ACTIVIDAD_DIRIGIDA) {
				// Y si es dirigida, el extra tiene que ser el nombre de la clase (Zumba, Yoga, etc.)
				if (extra.isBlank()) {
					vista.aviso("Debes escribir el nombre de la actividad dirigida.");
					return;
				}
				actividad = extra;
			}

			// Le pasamos toda la información ya convertida y limpia a la API
			boolean ok = api.crearReservaCompleta(usuario, id, fecha, hora, tipoEnum, numPart, actividad);

			// Le avisamos al usuario de lo que ha pasado
			if (ok) {
				vista.aviso("¡Reserva creada correctamente!");
			} else {
				vista.aviso("Error: Revisa que la instalación esté libre y el usuario exista.");
			}

		} catch (FechaIncorrectaException fex) {
			// Si se detecta en la fecha un error, se mostrara este mensaje
			vista.aviso("Error: La fecha debe tener formato numérico (DD/MM/AAAA).");

		} catch (NumberFormatException nfe) {
			// Si metieron letras en el ID de la instalación o en los participantes,mostraremos lo siguiente
			vista.aviso("Error: El ID de la instalación y los participantes deben ser solo números.");

		} catch (IllegalArgumentException iae) {
			// Y si el texto del tipo de reserva no cuadra con el Enum, saldra esto
			vista.aviso("Error: El tipo de reserva seleccionado no es válido.");
		}
	}
}