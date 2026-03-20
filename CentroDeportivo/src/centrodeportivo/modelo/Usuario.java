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
	
	public Usuario (String nombreCompleto,String telefono,String contrasena) {
		idUsuario = ++contador;
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

	public String toString() {
		return  "\nUsuario creado con éxito! \n"+
                "ID: " + getIdUsuario()+"\n"+
               "Nombre: " + getNombreCompleto()+"\n"
                + "Teléfono: " + getTelefono()+"\n";
	}

}
