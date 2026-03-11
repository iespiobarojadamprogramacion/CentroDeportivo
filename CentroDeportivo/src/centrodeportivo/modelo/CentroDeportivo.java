package centrodeportivo.modelo;

public class CentroDeportivo {

	private String nombre;
	private int capacidad;
	private String horario;
	private String direccion;

	public CentroDeportivo(String nombre, int capacidad, String horario, String direccion) {

		this.nombre = nombre;
		this.capacidad = capacidad;
		this.horario = horario;
		this.direccion = direccion;

	}

	public String getNombre() {
		return nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getHorario() {
		return horario;
	}

	public String getDireccion() {
		return direccion;
	}

}
