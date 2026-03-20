package centrodeportivo.modelo;

public class Reserva_Grupal extends Reserva {

	private int numMinimoParticipantes =2;
	private int numParticipantes;
	private int numMaximoParticipantes = 6;
	
	public Reserva_Grupal( String fecha, String horaInicio, String duracion, Estado_Reserva estado, int numParticipantes) {
		super(fecha, horaInicio, duracion, estado);
		this.setNumParticipantes(numParticipantes);
	}

	public int getNumMinimoParticipantes() {
		return numMinimoParticipantes;
	}

	public int getNumMaximoParticipantes() {
		return numMaximoParticipantes;
	}

	private String reglasUso;

	public String consultarReglasUso() {
		return reglasUso;
	}
	

	public void setReglasUso(String reglasUso) {
		this.reglasUso = "Para entrar a la actividad de uso Grupal "+", esperamos que siga las reglas especificadas por el centro"+
				"1. La impuntualidad hará que no se le re embolsé la reserva, contamos como tarde apartir de  15 minutos"+
				"2. El reservante principal sera responsable de todos los reservantes adicionales del grupo que sean menores, cualquier daño sera cobrado al principal "+
				"3. Si el reservante principal es sacado o sale del centro por cualquier razon todos los reservador adicionales deberan salir del centro"+
				"cualquier duda contacte por telefono, en recepcion o a un trabajador";
	}

	public int getNumParticipantes() {
		return numParticipantes;
	}

	public void setNumParticipantes(int numParticipantes) {
		this.numParticipantes = numParticipantes;
	}
	
}
