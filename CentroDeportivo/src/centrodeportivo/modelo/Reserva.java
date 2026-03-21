package centrodeportivo.modelo;

public abstract class Reserva {

	private static int contador = 0;
	private int idReserva;
	private String fecha;
	private String horaInicio;
	private String duracion;
	private Estado_Reserva estado;

	// Atributos de relación

	private Usuario usuario;
	private Instalacion instalacion;

	public Reserva(String fecha, String horaInicio, String duracion, Estado_Reserva estado, Usuario usuario,
			Instalacion instalacion) {

		this.idReserva = ++contador;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.duracion = duracion;
		this.estado = estado;
		this.usuario = usuario;
		this.instalacion = instalacion;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Estado_Reserva getEstado() {
		return estado;
	}

	public void setEstado(Estado_Reserva estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	// Método abstracto

	// Cada hija (individual, grupal, dirigida) lo implementará a su manera

	public abstract String consultarReglasUso();
}