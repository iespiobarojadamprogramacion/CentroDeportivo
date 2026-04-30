package centrodeportivo.modelo;

import java.util.ArrayList;

public class APICentroDeportivo {

	private static APICentroDeportivo api;
	private CentroDeportivo centro;

	private APICentroDeportivo() {
	
		centro = CentroDeportivo.getInstancia();
	}

	public static APICentroDeportivo getInstance() {
		if (api == null) {
			api = new APICentroDeportivo();
		}
		return api;
	}

	public boolean registrarUsuario(String nombre, String telefono, String contrasena) {
		Usuario nuevo = new Usuario(nombre, telefono, contrasena);
		return centro.registrarUsuario(nuevo);
	}

	public String[][] consultarInstalaciones() {
		Instalacion[] instalaciones = centro.getInstalacionesOrdenadasPorId();
		String[][] resultado = new String[instalaciones.length][3];
		for (int i = 0; i < instalaciones.length; i++) {
			resultado[i][0] = String.valueOf(instalaciones[i].getIdInstalacion());
			resultado[i][1] = instalaciones[i].getNombre();
			resultado[i][2] = String.valueOf(instalaciones[i].getTipo());
		}
		return resultado;
	}

	public boolean cancelarReserva(String idReservaVista) {
		try {
			int id = Integer.parseInt(idReservaVista);
			return centro.cancelarReserva(id);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String[] consultarOcupacion(String fecha) {
		return centro.consultarOcupacionDiaria(fecha);
	}

	public boolean eliminarUsuario(String nombre, String contrasena) {
		return centro.eliminarUsuario(nombre, contrasena);
	}


	public boolean crearReservaCompleta(String nombreUsuario, int idIns, String fecha, String hora, String tipo,
			int participantes, String actividad) {
		Usuario user = null;
		// Buscamos al usuario en la lista del centro
        for (Usuario u : centro.getUsuarios()) {
            if (u.getNombreCompleto().equalsIgnoreCase(nombreUsuario)) {
                user = u;
                break;
            }
        }
        
        Instalacion ins = null;
        // Buscamos la instalación comparando el nombre o el ID
        Instalacion[] listaIns = centro.getInstalacionesOrdenadasPorId();
        for (int i = 0; i < listaIns.length; i++) {
            if (listaIns[i].getIdInstalacion() == idIns) {
                ins = listaIns[i];
                break;
            }
        }

		if (user != null && ins != null) {
			try {
				// Convertimos el texto al tipo de reserva 
				Tipo_Reserva tipoEnum = Tipo_Reserva.valueOf(tipo);

				// Llamada al método del centro 
				
				return centro.crearReserva(user, ins, fecha, hora, "1h", Estado_Reserva.ACTIVA, "Sin monitor", actividad, participantes, tipoEnum);
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
}