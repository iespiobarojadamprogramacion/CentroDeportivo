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


	public Reserva[] visualizarReservasPorInstalacion(int idInstalacion, String fecha) {

		List<Reserva> encontradas = new ArrayList<>();

		for (Reserva r : reservas) {

			if (r.getFecha().equals(fecha)) {
				encontradas.add(r);
			}
		}

		return encontradas.toArray(new Reserva[0]);
	}

	public boolean registrarReserva(Usuario usuario, Instalacion instalacion, String fecha, String horaInicio) {

		if (usuario == null || instalacion == null) {
			return false;
		}

		if (instalacion.verificarDisponibilidad(fecha, horaInicio)) {

			System.out.println("Reserva registrada para " + usuario.getNombreCompleto());

			return true;

		}

		return false;

	}

	public String[] consultarOcupacionDiaria(String fecha) {
	    int contador = 0;

	    for (Reserva r : reservas) {
	        if (r.getFecha().equals(fecha) && r.getEstado() == Estado_Reserva.ACTIVA) {
	            contador++;
	        }
	    }

	    String info = "Informe del día " + fecha + ": Hay " + contador + " reservas confirmadas.";
	    return new String[]{info};
	}


	public String[] consultarOcupacionSemanal(String[] fechasSemana) {
	    String[] resultados = new String[fechasSemana.length];

	    for (int i = 0; i < fechasSemana.length; i++) {
	        int contador = 0;
	        String fecha = fechasSemana[i];

	        for (Reserva r : reservas) {
	            if (r.getFecha().equals(fecha) && r.getEstado() == Estado_Reserva.ACTIVA) {
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
	        return new String[]{"No hay horarios disponibles."};
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

	    if (libres.isEmpty()) {
	        return new String[]{"No hay tramos libres el " + fecha};
	    }

	    return libres.toArray(new String[0]);
	}


	public boolean crearReserva(Instalacion instalacion, String fecha, String horaInicio, String duracion, Estado_Reserva estado, 
	 String monitor, String nombreActividad, int numParticipantes, Tipo_Reserva tipo) {

		 if (!instalacion.verificarDisponibilidad(fecha, horaInicio)) {
		     return false;
		  }

		 for (Reserva r : reservas) {
		     if (r.getFecha().equals(fecha) && r.getHoraInicio().equals(horaInicio)) {
		    	 if (r.getEstado() == Estado_Reserva.ACTIVA) {
		             return false;
		            }
		        }
		    }

		Reserva nueva = null;

		switch(tipo) {

		   case INDIVIDUAL:
		       nueva = new Reserva_Individual(fecha, horaInicio, duracion, estado);
		       break;

		   case GRUPAL:
		       nueva = new Reserva_Grupal(fecha, horaInicio, duracion, estado, numParticipantes);
		       break;

		   case ACTIVIDAD_DIRIGIDA:
		        nueva = new Reserva_Actividad_Dirigida(fecha, horaInicio, duracion, estado, monitor, nombreActividad);
		        break;
		    }

		    if (nueva == null) {
		        return false;
		        }

		 reservas.add(nueva);
		 instalacion.addReserva(nueva);
		 return true;
		}
	
	public Instalacion[] getInstalacionesOrdenadasPorId() {
	    Instalacion[] copia = Arrays.copyOf(intalaciones, intalaciones.length); // copia del array
	    Arrays.sort(copia, Comparator.comparingInt(Instalacion::getIdInstalacion));
	    return copia;
	}


	public boolean cancelarReserva(int idReserva) {
		for (Reserva r : reservas) {
            if (r.getIdReserva() == idReserva) {
                reservas.remove(r);
                return true;
            }
        }
        return false;
	}
	public boolean modificarReserva(int idReserva, String fecha, String horaInicio) {
	    for (Reserva r : reservas) {
	        if (r.getIdReserva() == idReserva) {
	            r.setFecha(fecha);
	            r.setHoraInicio(horaInicio);
	            return true;
	        }
	    }
	    return false; 
	}
	public Reserva[] consultarHistorialUso() {
		return reservas.toArray(new Reserva[0]);
	}
	

}
