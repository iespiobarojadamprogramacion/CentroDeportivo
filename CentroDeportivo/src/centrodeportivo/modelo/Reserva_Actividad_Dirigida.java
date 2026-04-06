package centrodeportivo.modelo;

// Clase hija que representa una reserva para una actividad dirigida con monitor 

public class Reserva_Actividad_Dirigida extends Reserva {

	private String nombreActividad;

	// Lista de monitores 
	
	private static String[] monitores = { "Juan", "Ana", "Carlos", "Lucas", "Martin", "Alejandra", "Maria" };

	private String monitor;

	// Constructor que pasa los datos base al padre y asigna un monitor automáticamente
	
	public Reserva_Actividad_Dirigida(String fecha, String horaInicio, String duracion, Estado_Reserva estado,
			Usuario usuario, Instalacion instalacion, String nombreActividad) {

		// Llamada al constructor de la clase padre
		
		super(fecha, horaInicio, duracion, estado, usuario, instalacion);

		this.nombreActividad = nombreActividad;

		// Asignamos un monitor aleatorio de la lista estática
		
		int num = (int) (Math.random() * monitores.length);
		this.monitor = monitores[num];
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public String getMonitor() {
		return monitor;
	}

	// Sobreescribimos el método abstracto con las reglas específicas para esta actividad
	
	@Override
	public String consultarReglasUso() {
		return "Reglas para '" + nombreActividad + "' con el monitor/a " + monitor + ":\n"
				+ "1. La impuntualidad supone la pérdida de la plaza sin reembolso.\n"
				+ "2. Teléfonos y dispositivos electrónicos deben guardarse durante la sesión.\n"
				+ "3. Se debe seguir la autoridad del instructor en todo momento por seguridad.\n"
				+ "Cualquier duda adicional, consulte con el monitor antes de empezar.";
	}
}
