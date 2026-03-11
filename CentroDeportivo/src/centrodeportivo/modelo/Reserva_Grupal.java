package centrodeportivo.modelo;

public class Reserva_Grupal extends Reserva {

	private int numMinimoParticipantes;
	private int numMaximoParticipantes;
	
	public Reserva_Grupal(int idReserva, String fecha, String horaInicio, String duracion, Estado_Reserva estado, int numMinimoParticipantes, int numMaximoParticipantes) {
		super(idReserva, fecha, horaInicio, duracion, estado);
		this.numMinimoParticipantes=numMinimoParticipantes;
		this.numMaximoParticipantes=numMaximoParticipantes;
	}

	public int getNumMinimoParticipantes() {
		return numMinimoParticipantes;
	}

	public void setNumMinimoParticipantes(int numMinimoParticipantes) {
		this.numMinimoParticipantes = numMinimoParticipantes;
	}

	public int getNumMaximoParticipantes() {
		return numMaximoParticipantes;
	}

	public void setNumMaximoParticipantes(int numMaximoParticipantes) {
		this.numMaximoParticipantes = numMaximoParticipantes;
	}

	private String reglasUso;

	public void setReglasUso(String reglasUso) {
		this.reglasUso = reglasUso;
	}

	public String consultarReglasUso() {
		return reglasUso;
	}
	
	
}
