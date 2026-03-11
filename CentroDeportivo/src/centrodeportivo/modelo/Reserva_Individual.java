package centrodeportivo.modelo;

public class Reserva_Individual extends Reserva {

	public Reserva_Individual(int idReserva, String fecha, String horaInicio, String duracion, Estado_Reserva estado) {
		super(idReserva, fecha, horaInicio, duracion, estado);
	}

	private String reglasUso;

	public void setReglasUso(String reglasUso) {
		this.reglasUso = reglasUso;
	}

	public String consultarReglasUso() {
		return reglasUso;
	}
	
}
