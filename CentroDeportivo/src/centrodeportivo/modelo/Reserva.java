package centrodeportivo.modelo;

import java.util.ArrayList;

public abstract class Reserva {

	
	private static int contador=0;
	private int idReserva;
	private String fecha;
	private String horaInicio;
	private String duracion;
	private Estado_Reserva estado;
	public int idBuscado;
	
	private static ArrayList<Reserva> todasLasReservas = new ArrayList<>();
	
	public Reserva(int idReserva, String fecha, String horaInicio, String duracion, Estado_Reserva estado){
		this.idReserva = ++ contador;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.duracion = duracion;
		this.estado = estado;
		
		todasLasReservas.add(this);
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

	public abstract String consultarReglasUso();
	
	                     
	}
