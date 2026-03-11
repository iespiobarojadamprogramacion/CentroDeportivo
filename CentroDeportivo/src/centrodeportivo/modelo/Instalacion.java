package centrodeportivo.modelo;

public class Instalacion {
	
	private int idInstalacion;
	private String nombre;
	private int capacidadMaxima;
	private TipoInstalacion tipo;
	private String horarioDisponibilidad;
	
	public Instalacion (int idInstalacion, String nombre, int capacidadMaxima,TipoInstalacion tipo,String horarioDisponibilidad) {
		
		this.idInstalacion = idInstalacion ;
		this.nombre = nombre ;
		this.capacidadMaxima = capacidadMaxima;
		this.tipo = tipo;
		this.horarioDisponibilidad = horarioDisponibilidad;
			
	}

	public int getIdInstalacion() {
		return idInstalacion;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public TipoInstalacion getTipo() {
		return tipo;
	}

	public String getHorarioDisponibilidad() {
		return horarioDisponibilidad;
	}
}


