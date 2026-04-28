package centrodeportivo.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CentroDeportivo {
	
	//PRUEBA

	// Variable para que todo el programa comparta un único centro deportivo 

	private static CentroDeportivo instancia;

	private String nombre;
	private int capacidad;
	private String horario;
	private String direccion;

	// Listas y arrays para gestionar los datos del centro

	private Instalacion[] instalaciones;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Reserva> reservas;

	public CentroDeportivo() {
		this.nombre = "Polideportivo ESP";
		this.capacidad = 200;
		this.horario = "8:00-22:00";
		this.direccion = "C/ La Mentirosa 420";
		this.usuarios = new ArrayList<>();
		this.reservas = new ArrayList<>();

		// Inicialización de instalaciones por defecto

		this.instalaciones = new Instalacion[] {
				new Instalacion("Piscina Olimpica A-1", 100, TipoInstalacion.PISCINA_OLIMPICA,
						"10:00 a 11:00,17:00 a 18:00"),
				new Instalacion("Pista Tenis B-3", 100, TipoInstalacion.PISTA_TENIS, "10:00 a 11:00,14:00 a 15:00"),
				new Instalacion("Pista Padel B-1", 100, TipoInstalacion.PISTA_PADEL, "9:30 a 10:30,13:30 a 14:30"),
				new Instalacion("Pista Baloncesto B-2", 100, TipoInstalacion.PISTA_BALONCESTO,
						"11:00 a 12:00,15:00 a 16:00"),
				new Instalacion("Sala Polivalente C-1", 100, TipoInstalacion.SALA_POLIVALENTE,
						"9:00 a 11:00,14:00 a 15:00"),
				new Instalacion("Gimnasio C-2", 100, TipoInstalacion.GIMNASIO, "10:00 a 11:00,16:00 a 17:00") };
	}

	// Método estático para obtener la instancia única

	public static CentroDeportivo getInstancia() {
		if (instancia == null) {
			instancia = new CentroDeportivo();
		}
		return instancia;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getHorario() {
		return horario;
	}

	public String getDireccion() {
		return direccion;
	}

	// Registra un nuevo usuario en el sistema

	public boolean registrarUsuario(Usuario nuevoUsuario) {

	    if (nuevoUsuario == null) return false;

	    if (nuevoUsuario.getNombreCompleto().isBlank()
	            || nuevoUsuario.getContrasena().isBlank()
	            || nuevoUsuario.getTelefono().isBlank()) {
	        return false;
	    }

	    for (Usuario u : usuarios) {
	        if (u.getNombreCompleto().equalsIgnoreCase(nuevoUsuario.getNombreCompleto())) {
	            return false; // duplicado
	        }
	    }

	    usuarios.add(nuevoUsuario);
	    return true;
	}


	// Elimina un usuario si el nombre y contraseña coinciden
	public boolean eliminarUsuario(String nombre, String contrasena) {

	    for (Usuario u : usuarios) {
	        if (u.getNombreCompleto().equals(nombre)
	                && u.getContrasena().equals(contrasena)) {

	            usuarios.remove(u);
	            reservas.removeIf(r -> r.getUsuario().equals(u));

	            return true;
	        }
	    }

	    return false;
	}


	// Crea una reserva usando polimorfismo según el tipo

	public boolean crearReserva(Usuario usuario, Instalacion instalacion, String fecha, String horaInicio,
			String duracion, Estado_Reserva estado, String monitor, String nombreActividad, int numParticipantes,
			Tipo_Reserva tipo) {
		
		if (usuario == null || instalacion == null) {
		    return false;
		}

		if (!instalacion.verificarDisponibilidad(fecha, horaInicio)) {
			return false;
		}

		Reserva nuevaReserva = null;
		switch (tipo) {
		case INDIVIDUAL:
			nuevaReserva = new Reserva_Individual(fecha, horaInicio, duracion, estado, usuario, instalacion);
			break;
		case GRUPAL:
			nuevaReserva = new Reserva_Grupal(fecha, horaInicio, duracion, estado, usuario, instalacion,
					numParticipantes);
			break;
		case ACTIVIDAD_DIRIGIDA:
			nuevaReserva = new Reserva_Actividad_Dirigida(fecha, horaInicio, duracion, estado, usuario, instalacion,
					nombreActividad);
			break;
		}

		if (nuevaReserva != null) {
			this.reservas.add(nuevaReserva);
			instalacion.addReserva(nuevaReserva);
			usuario.addReserva(nuevaReserva);
			return true;
		}
		return false;
	}

	// Cambia el estado de una reserva a "CANCELADA"

	public boolean cancelarReserva(int idReserva) {
		for (Reserva r : reservas) {
			if (r.getIdReserva() == idReserva) {
				if (r.getEstado() == Estado_Reserva.CANCELADA) return false;
				r.setEstado(Estado_Reserva.CANCELADA);
			}
		}
		return false;
	}

	// Modifica los datos de una reserva si hay disponibilidad

	public boolean modificarReserva(int idReserva, String fecha, String tramohoras, Instalacion nuevaInstalacion) {
		for (Reserva r : reservas) {
			if (r.getIdReserva() == idReserva) {
				
				if (r.getEstado() != Estado_Reserva.ACTIVA) {
				    return false;
				}

				if (nuevaInstalacion.verificarDisponibilidad(fecha, tramohoras)) {
					r.setFecha(fecha);
					r.setHoraInicio(tramohoras);
					r.setInstalacion(nuevaInstalacion);
					return true;
				}
			}
		}
		return false;
	}

	// Devuelve las reservas de una fecha para una instalación concreta

	public Reserva[] visualizarReservasPorInstalacion(int idInstalacion, String fecha) {
		List<Reserva> encontradas = new ArrayList<>();
		for (Reserva r : reservas) {
			if (r.getFecha().equals(fecha) && r.getInstalacion().getIdInstalacion() == idInstalacion) {
				encontradas.add(r);
			}
		}
		return encontradas.toArray(new Reserva[0]);
	}

	// Devuelve el resumen de ocupación de un día

	public String[] consultarOcupacionDiaria(String fecha) {
		int contador = 0;
		for (Reserva r : reservas) {
			if (r.getFecha().equals(fecha)
					&& (r.getEstado() == Estado_Reserva.ACTIVA || r.getEstado() == Estado_Reserva.COMPLETADA)) {
				contador++;
			}
		}
		return new String[] { "Ocupación " + fecha + ": " + contador + " reservas." };
	}

	// Devuelve la ocupación de varios días

	public String[] consultarOcupacionSemanal(String[] fechasSemana) {
		String[] resultados = new String[fechasSemana.length];
		for (int i = 0; i < fechasSemana.length; i++) {
			int contador = 0;
			String f = fechasSemana[i];
			for (Reserva r : reservas) {
				if (r.getFecha().equals(f)
						&& (r.getEstado() == Estado_Reserva.ACTIVA || r.getEstado() == Estado_Reserva.COMPLETADA)) {
					contador++;
				}
			}
			resultados[i] = "Día " + f + ": " + contador + " reservas.";
		}
		return resultados;
	}

	// Identifica qué horas están libres en una instalación

	public String[] identificarTramosLibres(Instalacion instalacion, String fecha) {
		List<String> libres = new ArrayList<>();
		String horarioDisp = instalacion.getHorarioDisponibilidad();
		if (horarioDisp == null || horarioDisp.isEmpty())
			return new String[0];

		String[] tramos = horarioDisp.split(",");
		for (String hora : tramos) {
			if (instalacion.verificarDisponibilidad(fecha, hora.trim())) {
				libres.add(hora.trim());
			}
		}
		return libres.toArray(new String[0]);
	}

	// Devuelve las instalaciones ordenadas (útil para el menú)

	public Instalacion[] getInstalacionesOrdenadasPorId() {
		Instalacion[] copia = Arrays.copyOf(instalaciones, instalaciones.length);
		Arrays.sort(copia, Comparator.comparingInt(Instalacion::getIdInstalacion));
		return copia;
	}
}
