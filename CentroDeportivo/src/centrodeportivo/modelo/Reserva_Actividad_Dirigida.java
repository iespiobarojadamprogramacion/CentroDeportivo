package centrodeportivo.modelo;

public class Reserva_Actividad_Dirigida extends Reserva {

	private String nombreActividad;
	private String monitor;

	// Lista de monitores

	public static String[] monitores = { "Juan", "Ana", "Carlos", "Lucas", "Martin", "Alejandra", "Maria" };
	

	public String getNombreActividad() {
		return nombreActividad;
	}

	public String getMonitor() {
		return monitor;
	}

	// Constructor que pasa usuario e instalacion al padre

	public Reserva_Actividad_Dirigida(String fecha, String horaInicio, String duracion, Estado_Reserva estado,
			Usuario usuario, Instalacion instalacion, String nombreActividad) {

		super(fecha, horaInicio, duracion, estado, usuario, instalacion);

		int num = (int) (Math.random() * monitores.length);
		this.monitor = monitores[num]; // Asignamos el monitor aleatorio de la lista
		this.nombreActividad = nombreActividad;
	}

	// Implementamos el método abstracto

	public String consultarReglasUso() {
		return "Reglas para '" + nombreActividad + "' con el monitor/a " + monitor + ":\n"
				+ "1. La impuntualidad supone la pérdida de la plaza sin reembolso.\n"
				+ "2. Teléfonos y dispositivos electrónicos deben guardarse durante la sesión.\n"
				+ "3. Se debe seguir la autoridad del instructor en todo momento por seguridad.\n"
				+ "Cualquier duda adicional, consulte con el monitor antes de empezar.";
	}
}