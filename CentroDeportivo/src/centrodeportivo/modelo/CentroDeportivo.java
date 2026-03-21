package centrodeportivo.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CentroDeportivo {

private static CentroDeportivo instancia;

	private String nombre;
	private int capacidad;
	private String horario;
	private String direccion;

	// Reflejo de las relaciones que se encuentran en el diagrama
    private static CentroDeportivo instancia;

    private String nombre;
    private int capacidad;
    private String horario;
    private String direccion;

    private List<Usuario> usuarios;
    private List<Reserva> reservas;
    private Instalacion[] intalaciones;

    public CentroDeportivo() {
        this.nombre = "Polideportivo ESP";
        this.capacidad = 200;
        this.horario = "8:00-22:00";
        this.direccion = "C/ La Mentirosa 420";
        this.usuarios = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.intalaciones = new Instalacion[]{
        		new Instalacion("Piscina Olimpica A-1", 100, TipoInstalacion.PISCINA_OLIMPICA, "9:00 a 12:00,13:00 a 15:00"),
        		new Instalacion("Pista Tenis B-3", 100, TipoInstalacion.PISTA_TENIS, "10:00 a 12:30,14:00 a 16:00"),
        		new Instalacion("Pista Padel B-1", 100, TipoInstalacion.PISTA_PADEL, "9:30 a 11:30,13:30 a 15:30"),
        		new Instalacion("Pista Baloncesto B-2", 100, TipoInstalacion.PISTA_BALONCESTO, "11:00 a 13:00,15:00 a 17:00"),
        		new Instalacion("Sala Polivalente C-1", 100, TipoInstalacion.SALA_POLIVALENTE, "9:00 a 11:00,14:00 a 16:00"),
        		new Instalacion("Gimnasio C-2", 100, TipoInstalacion.GIMNASIO, "10:00 a 12:00,16:00 a 18:00"),

        };
    }

    public static CentroDeportivo getInstancia() {
        if (instancia == null) {
            instancia = new CentroDeportivo();
        }
        return instancia;
    }

	private Instalacion[] instalaciones;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Reserva> reservas;

	// Constructor vacío (inicializa con datos por defecto)

	public CentroDeportivo() {
		this.nombre = "Polideportivo ESP";
		this.capacidad = 200;
		this.horario = "8:00-22:00";
		this.direccion = "C/ La Mentirosa 420";
		this.usuarios = new ArrayList<>();
		this.reservas = new ArrayList<>();

		// Inicializamos el array fijo de instalaciones

		this.instalaciones = new Instalacion[] {
				new Instalacion("Piscina Olimpica A-1", 100, TipoInstalacion.PISCINA_OLIMPICA,
						"9:00 a 12:00,13:00 a 15:00"),
				new Instalacion("Pista Tenis B-3", 100, TipoInstalacion.PISTA_TENIS, "10:00 a 12:30,14:00 a 16:00"),
				new Instalacion("Pista Padel B-1", 100, TipoInstalacion.PISTA_PADEL, "9:30 a 11:30,13:30 a 15:30"),
				new Instalacion("Pista Baloncesto B-2", 100, TipoInstalacion.PISTA_BALONCESTO,
						"11:00 a 13:00,15:00 a 17:00"),
				new Instalacion("Sala Polivalente C-1", 100, TipoInstalacion.SALA_POLIVALENTE,
						"9:00 a 11:00,14:00 a 16:00"),
				new Instalacion("Gimnasio C-2", 100, TipoInstalacion.GIMNASIO, "10:00 a 12:00,16:00 a 18:00") };
	}

	// Constructor con parámetros

	public CentroDeportivo(String nombre, int capacidad, String horario, String direccion) {
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.horario = horario;
		this.direccion = direccion;
		this.usuarios = new ArrayList<>();
		this.reservas = new ArrayList<>();
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

	// Gestión de los usuarios

	public void registrarUsuario(Usuario nuevoUsuario) {
		if (nuevoUsuario != null) {
			usuarios.add(nuevoUsuario);
		}
	}
	
	public boolean eliminarUsuario(String nombre, String contrasena) {
	    for (int i = 0; i < usuarios.size(); i++) {
	        Usuario u = usuarios.get(i);
	        if (u.getNombreCompleto().equals(nombre) && u.getContrasena().equals(contrasena)) {
	            usuarios.remove(i);
	            return true; // eliminado con éxito
	        }
	    }
	    return false; // no se encontró usuario con esos datos
	}


	public boolean eliminarUsuario(String nombre, String contrasena) {
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario u = usuarios.get(i);
			if (u.getNombreCompleto().equals(nombre) && u.getContrasena().equals(contrasena)) {
				usuarios.remove(i);
				return true;
			}
		}
		return false;
	}

	// Gestión de las reservas

	// Método principal para crear la reserva

	public boolean crearReserva(Usuario usuario, Instalacion instalacion, String fecha, String horaInicio,
			String duracion, Estado_Reserva estado, String monitor, String nombreActividad, int numParticipantes,
			Tipo_Reserva tipo) {

		// Valida que la instalación esté libre

		if (!instalacion.verificarDisponibilidad(fecha, horaInicio)) {
			return false;
		}

		// Crea el objeto reserva correspondiente según el Enum

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
					monitor, nombreActividad);
			break;
		}

		// Si se creó correctamente, la guardamos en los 3 sitios

		if (nuevaReserva != null) {
			this.reservas.add(nuevaReserva); // Lista del centro
			instalacion.addReserva(nuevaReserva); // Lista de la instalación
			usuario.addReserva(nuevaReserva); // Historial del usuario
			return true;
		}

		return false;
	}

	public boolean cancelarReserva(int idReserva) {
		for (Reserva r : reservas) {
			if (r.getIdReserva() == idReserva) {
				r.setEstado(Estado_Reserva.CANCELADA);
				return true;
			}
		}
		return false;
	}

	public boolean modificarReserva(int idReserva, String fecha, String horaInicio, Instalacion nuevaInstalacion) {
		for (Reserva r : reservas) {
			if (r.getIdReserva() == idReserva) {

				// Verificamos si la nueva instalación/horario está libre

				if (nuevaInstalacion.verificarDisponibilidad(fecha, horaInicio)) {
					r.setFecha(fecha);
					r.setHoraInicio(horaInicio);

					// Como la instalación es nueva, actualizamos la referencia en la reserva

					r.setInstalacion(nuevaInstalacion);
					return true;
				}
			}
		}
		return false;
	}

	// Agenda y planificación

	public Reserva[] visualizarReservasPorInstalacion(int idInstalacion, String fecha) {
		List<Reserva> encontradas = new ArrayList<>();
		for (Reserva r : reservas) {

			// Comprobamos la fecha y que la instalación de esa reserva sea la que buscamos

			if (r.getFecha().equals(fecha) && r.getInstalacion().getIdInstalacion() == idInstalacion) {
				encontradas.add(r);
			}
		}
		return encontradas.toArray(new Reserva[0]);
	}

	public String[] consultarOcupacionDiaria(String fecha) {
		int contador = 0;
		for (Reserva r : reservas) {
			if (r.getFecha().equals(fecha)
					&& (r.getEstado() == Estado_Reserva.ACTIVA || r.getEstado() == Estado_Reserva.COMPLETADA)) {
				contador++;
			}
		}

		String info = "Informe del día " + fecha + ": Hay " + contador + " reservas confirmadas.";
		return new String[] { info };
	}

	public String[] consultarOcupacionSemanal(String[] fechasSemana) {
		String[] resultados = new String[fechasSemana.length];
		for (int i = 0; i < fechasSemana.length; i++) {
			int contador = 0;
			String fecha = fechasSemana[i];
			for (Reserva r : reservas) {
				if (r.getFecha().equals(fecha)
						&& (r.getEstado() == Estado_Reserva.ACTIVA || r.getEstado() == Estado_Reserva.COMPLETADA)) {
					contador++;
				}
			}
			resultados[i] = "Ocupación para " + fecha + ": " + contador + " reservas activas.";
		}
		return resultados;
	}

	public String[] identificarTramosLibres(Instalacion instalacion, String fecha) {
		List<String> libres = new ArrayList<>();
		String horario = instalacion.getHorarioDisponibilidad();

		if (horario == null || horario.isEmpty()) {
			return new String[] { "No hay horarios disponibles." };
		}

		String[] tramos = horario.split(",");

		for (String hora : tramos) {
			boolean ocupado = false;
			for (Reserva r : instalacion.getReservas()) {
				if (r.getFecha().equals(fecha) && r.getHoraInicio().equals(hora)
						&& r.getEstado() == Estado_Reserva.ACTIVA) {
					ocupado = true;
					break;
				}
			}
			if (!ocupado) {
				libres.add(hora);
			}
		}
	
	public Instalacion[] getInstalacionesOrdenadasPorId() {
	    Instalacion[] copia = Arrays.copyOf(intalaciones, intalaciones.length); // copia del array
	    Arrays.sort(copia, Comparator.comparingInt(Instalacion::getIdInstalacion));
	    return copia;
	}

		if (libres.isEmpty()) {
			return new String[] { "No hay tramos libres el " + fecha };
		}
		return libres.toArray(new String[0]);
	}
	

	// Método para mostrar el menú

	public Instalacion[] getInstalacionesOrdenadasPorId() {
		Instalacion[] copia = Arrays.copyOf(instalaciones, instalaciones.length);
		Arrays.sort(copia, Comparator.comparingInt(Instalacion::getIdInstalacion));
		return copia;
	}
}