package centrodeportivo.modelo;

import java.util.List;

public class Instalacion {
	
	private int idInstalacion;
	private String nombre;
	private int capacidadMaxima;
	private TipoInstalacion tipo ;
	private String horarioDisponibilidad;
	private List < Reserva > reservas;
	
	public Instalacion (int idInstalacion, String nombre, int capacidadMaxima,TipoInstalacion tipo,String horarioDisponibilidad) {
		
		this.idInstalacion = idInstalacion ;
		this.nombre = nombre ;
		this.capacidadMaxima = capacidadMaxima ;
		this.tipo = tipo ;
		this.horarioDisponibilidad = horarioDisponibilidad ;
		
		
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
	
	public boolean verificarDisponibilidad(String fecha, String hora) {
		
		if (this.horarioDisponibilidad == null || !this.horarioDisponibilidad.contains(hora)) {
			return false;
		}

		for (Reserva r : reservas) {
			if (r.getFecha().equals(fecha) && r.getHoraInicio().equals(hora)) {
				
				if (r.getEstado() == Estado_Reserva.ACTIVA) {
					return false;
				}
			}
		}

		return true; 
	}
}