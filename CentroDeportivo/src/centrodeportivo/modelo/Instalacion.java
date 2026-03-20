package centrodeportivo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Instalacion {
	private static int contador = 0;
	private int idInstalacion;
	private String nombre;
	private int capacidadMaxima;
	private TipoInstalacion tipo_i ;
	private String horarioDisponibilidad;
	private List < Reserva > reservas;
	
	public Instalacion(String nombre, int capacidadMaxima, TipoInstalacion tipo_i, String horarioDisponibilidad) {
	    idInstalacion = ++contador;
	    this.nombre = nombre;
	    this.capacidadMaxima = capacidadMaxima;
	    this.tipo_i = tipo_i;
	    this.horarioDisponibilidad = horarioDisponibilidad;
	    this.reservas = new ArrayList<>();
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
		return tipo_i;
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

	public void addReserva(Reserva r) {
	    reservas.add(r);
	}

	public List<Reserva> getReservas() {
	    return reservas;
	}

}