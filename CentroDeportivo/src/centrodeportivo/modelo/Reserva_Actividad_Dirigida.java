package centrodeportivo.modelo;

public class Reserva_Actividad_Dirigida extends Reserva {

	private String nombreActividad;
	private String monitor;
	
	public Reserva_Actividad_Dirigida(int idReserva, String fecha, String horaInicio, String duracion,
			Estado_Reserva estado, String monitor, String nombreActividad) {
		super(idReserva, fecha, horaInicio, duracion, estado);
		this.monitor=monitor;
		this.nombreActividad=nombreActividad;
	}
	
	private String reglasUso;

	public void setReglasUso(String reglasUso) {
		this.reglasUso = reglasUso;
	}

	public String consultarReglasUso() {
		return reglasUso;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	
}
