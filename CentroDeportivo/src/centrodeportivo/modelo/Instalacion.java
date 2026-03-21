package centrodeportivo.modelo;

import java.util.ArrayList;

public class Instalacion {

	private static int contador = 0;
	private int idInstalacion;
	private String nombre;
	private TipoInstalacion tipo;
	private int capacidadMaxima;
	private String horarioDisponibilidad;
	private ArrayList<Reserva> reservas;

	public Instalacion(String nombre, int capacidadMaxima, TipoInstalacion tipo, String horarioDisponibilidad) {

		this.idInstalacion = ++contador;
		this.nombre = nombre;
		this.capacidadMaxima = capacidadMaxima;
		this.tipo = tipo;
		this.horarioDisponibilidad = horarioDisponibilidad;

		// Inicializamos la lista vacía

		this.reservas = new ArrayList<>();
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

	// Este método es necesario para que el centro deportivo lea las reservas

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public boolean verificarDisponibilidad(String fecha, String hora) {

		// Primero miramos si esa hora existe en el horario de la instalación

		if (this.horarioDisponibilidad == null || !this.horarioDisponibilidad.contains(hora)) {
			return false;
		}

		// Si la hora es válida, miramos si ya hay alguien que haya reservado y esté "ACTIVA"

		for (Reserva r : reservas) {

			if (r.getFecha().equals(fecha) && r.getHoraInicio().equals(hora)) {

				if (r.getEstado() == Estado_Reserva.ACTIVA) {

					return false; // Está ocupada
				}
			}
		}

		return true; // Si pasa todos los filtros, está libre
	}

	// Método para que el centro deportivo vincule la reserva a esta instalación

	public void addReserva(Reserva reserva) {
		if (reserva != null) {
			this.reservas.add(reserva);
		}
	}
}