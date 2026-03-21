package centrodeportivo.modelo;

public class Reserva_Actividad_Dirigida extends Reserva {

	private String nombreActividad;
	private String monitor;

	// El constructor recibe todo lo de reserva más monitor y nombre de la actividad
	public static String [] monitores = {"Juan","Ana","Carlos",
			"Lucas", "Martin", "Alejandra", "Maria"};
	
	public Reserva_Actividad_Dirigida(String fecha, String horaInicio, String duracion,
		Estado_Reserva estado, String monitor, String nombreActividad) {
		super(fecha, horaInicio, duracion, estado);
		int num = (int)(Math.random() * monitores.length);
		this.monitor = monitores[num];
		this.nombreActividad=nombreActividad;
	
	}

	public Reserva_Actividad_Dirigida(String fecha, String horaInicio, String duracion, Estado_Reserva estado,
			Usuario usuario, Instalacion instalacion, String monitor, String nombreActividad) {

		super(fecha, horaInicio, duracion, estado, usuario, instalacion);
		this.monitor = monitor;
		this.nombreActividad = nombreActividad;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public String getMonitor() {
		return String.valueOf(monitor);
	}

	// Implementamos el método abstracto con las reglas específicas para esta clase
	public String consultarReglasUso() {
		return "Reglas para '" + nombreActividad + "' con el monitor/a " + monitor + ":\n"
				+ "1. La impuntualidad supone la pérdida de la plaza sin reembolso.\n"
				+ "2. Teléfonos y dispositivos electrónicos deben guardarse durante la sesión.\n"
				+ "3. Se debe seguir la autoridad del instructor en todo momento por seguridad.\n"
				+ "Cualquier duda adicional, consulte con el monitor antes de empezar.";
	}
}