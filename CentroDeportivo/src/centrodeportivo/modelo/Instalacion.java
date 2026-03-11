package centrodeportivo.modelo;

public class Instalacion {

	private int idInstalacion;
	private String nombre;
	private TipoInstalacion tipo;
	private int capacidadMaxima;
	private String horarioDisponibilidad;

	public Instalacion(int idInstalacion, String nombre, TipoInstalacion tipo, int capacidadMaxima,
			String horarioDisponibilidad) {

		this.idInstalacion = idInstalacion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.capacidadMaxima = capacidadMaxima;
		this.horarioDisponibilidad = horarioDisponibilidad;

	}

	public int getIdInstalacion() {
		return idInstalacion;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoInstalacion getTipo() {
		return tipo;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public String getHorarioDisponibilidad() {
		return horarioDisponibilidad;
	}

}
