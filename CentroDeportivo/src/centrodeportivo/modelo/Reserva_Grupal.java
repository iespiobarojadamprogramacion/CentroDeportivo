package centrodeportivo.modelo;

public class Reserva_Grupal extends Reserva {

	private int numParticipantes;
	private int numMinimoParticipantes = 2;
	private int numMaximoParticipantes = 6;

	// El constructor recibe todo lo de reserva más los participantes

	public Reserva_Grupal(String fecha, String horaInicio, String duracion, Estado_Reserva estado, Usuario usuario,
			Instalacion instalacion, int numParticipantes) {

		super(fecha, horaInicio, duracion, estado, usuario, instalacion);
		this.numParticipantes = numParticipantes;
	}

	public int getNumParticipantes() {
		return numParticipantes;
	}

	public int getNumMinimoParticipantes() {
		return numMinimoParticipantes;
	}

	public int getNumMaximoParticipantes() {
		return numMaximoParticipantes;
	}

	// Implementamos el método abstracto con las reglas específicas

	@Override
	public String consultarReglasUso() {
		return "Reglas para Reserva Grupal (" + numParticipantes + " participantes):\n"
				+ "1. La impuntualidad no es reembolsable (tarde a partir de 15 min).\n"
				+ "2. El reservante principal es responsable de los menores y de cualquier daño.\n"
				+ "3. Si el responsable sale del centro, sus acompañantes también deben salir.\n"
				+ "Cualquier duda, contacte por teléfono o en recepción.";
	}
}