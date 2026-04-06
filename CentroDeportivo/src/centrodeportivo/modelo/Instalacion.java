package centrodeportivo.modelo;

import java.util.ArrayList;

public class Instalacion {

	// Variable estática para generar el ID autoincremental de la instalación 
	
	private static int contador = 0;

	private int idInstalacion;
	private String nombre;
	private TipoInstalacion tipo;
	private int capacidadMaxima;
	private String horarioDisponibilidad;

	// Lista que almacena las reservas asociadas a esta instalación
	
	private ArrayList<Reserva> reservas;

	// Constructor para inicializar la instalación y su ID único
	
	public Instalacion(String nombre, int capacidadMaxima, TipoInstalacion tipo, String horarioDisponibilidad) {
		this.idInstalacion = ++contador;
		this.nombre = nombre;
		this.capacidadMaxima = capacidadMaxima;
		this.tipo = tipo;
		this.horarioDisponibilidad = horarioDisponibilidad;

		// Inicializamos la lista de reservas vacía para evitar errores al añadir luego
		
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

	// Comprueba si la instalación está libre en una fecha y hora concretas
	
	public boolean verificarDisponibilidad(String fecha, String hora) {

		// Primero verificamos si la hora solicitada existe en el horario permitido
		
		if (this.horarioDisponibilidad == null || !this.horarioDisponibilidad.contains(hora)) {
			return false;
		}

		// Si la hora es válida, comprobamos si ya hay una reserva "ACTIVA" en ese momento
		
		for (Reserva r : reservas) {
			if (r.getFecha().equals(fecha) && r.getHoraInicio().equals(hora)) {
				if (r.getEstado() == Estado_Reserva.ACTIVA) {
					return false; // La instalación ya está ocupada
				}
			}
		}

		return true; // Si supera todas las comprobaciones, está libre
	}

	// Método para que el centro deportivo vincule la reserva a esta instalación
	
	public void addReserva(Reserva reserva) {
		if (reserva != null) {
			this.reservas.add(reserva);
		}
	}
}
