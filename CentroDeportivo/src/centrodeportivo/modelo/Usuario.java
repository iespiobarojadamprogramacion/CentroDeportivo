package centrodeportivo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private static int contador=0;
	private int idUsuario;
	private String nombreCompleto;
	private String telefono;
	private String contrasena;
	
	private CentroDeportivo centroDeportivo;
	private List<Reserva> reservas;
	
	public Usuario (int idUsuario,String nombreCompleto,String telefono,String contrasena) {
		contador++;
		this.idUsuario = contador;
		this.nombreCompleto=nombreCompleto;
		this.telefono=telefono;
		this.contrasena=contrasena;
		this.centroDeportivo = centroDeportivo;
        this.reservas = new ArrayList<>();
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public boolean crearReserva(Instalacion instalacion, String fecha, String horaInicio) {
		
		return true;
	}
	public boolean cancelarReserva(int idReserva) {
		for (Reserva r : reservas) {
            if (r.getIdReserva() == idReserva) {
                reservas.remove(r);
                return true;
            }
        }
        return false;
	}
	public boolean modificarReserva(int idReserva, String fecha, String horaInicio) {
	    for (Reserva r : reservas) {
	        if (r.getIdReserva() == idReserva) {
	            r.setFecha(fecha);
	            r.setHoraInicio(horaInicio);
	            return true;
	        }
	    }
	    return false; // Añadir al diagrama de clases los metodos sets en Reserva
	}
	public Reserva[] consultarHistorialUso() {
		return reservas.toArray(new Reserva[0]);
	}
}
