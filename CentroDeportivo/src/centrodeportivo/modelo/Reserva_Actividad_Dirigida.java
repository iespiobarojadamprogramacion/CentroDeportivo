package centrodeportivo.modelo;

public class Reserva_Actividad_Dirigida extends Reserva {

	private String nombreActividad;
	private String monitor;
	public static String [] monitores = {"Juan","Ana","Carlos",
			"Lucas", "Martin", "Alejandra", "Maria"};
	
	public Reserva_Actividad_Dirigida(String fecha, String horaInicio, String duracion,
		Estado_Reserva estado, String monitor, String nombreActividad) {
		super(fecha, horaInicio, duracion, estado);
		int num = (int)(Math.random() * monitores.length);
		this.monitor = monitores[num];
		this.nombreActividad=nombreActividad;
	
	}
	
	private String reglasUso;

	public void setReglasUso(String reglasUso) {
		this.reglasUso = "Para entrar a la actividad del monitor "+monitor+", esperamos que siga las reglas especificadas por el/ella."+
				"1. La impuntualidad es intolerable cualquier llegado fuera de tiempo perdera su oportunindad y no se le re embolsará la reserva"+
				"2. Durante la actividad todo telefono o dispositivo electronico se pondra en una mochila propia y solo se cogeran llamadas importantes"+
				"3. La autoridad de esta clase es del instructor/monitor y se hara lo que se pida, si no se quiere hacer o se opina excesivo puede salir y hablar con el centro"+
				"Alguna regla particular del monitos sera notificada durante la sesion.";
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
		return String.valueOf(monitor);
	}
	
	public String getReglasUso() {
		return reglasUso;
	}
	
}
