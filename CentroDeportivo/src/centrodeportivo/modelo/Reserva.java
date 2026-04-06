package centrodeportivo.modelo;

// Clase abstracta que representa la base para cualquier tipo de reserva 

public abstract class Reserva {

	// Variable estática para generar el ID autoincremental de la reserva
	
	private static int contador = 0;

	private int idReserva;
	private String fecha;
	private String horaInicio;
	private String duracion;
	private Estado_Reserva estado;

	// Atributos de relación 
	
	private Usuario usuario;
	private Instalacion instalacion;

	// Constructor que inicializa todos los datos de la reserva
	
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

	// Setter añadido para cumplir con modificar la fecha
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	// Nos ayudara a modificar el horario
	
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getDuracion() {
		return duracion;
	}

	// Modificar la duración
	
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

	// Setter también añadido para cumplir con el apartado 5 del PDF para modificar la instalación
	
	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	// Método abstracto que cada clase hija implementará con sus propias reglas
	
	public abstract String consultarReglasUso();
}
