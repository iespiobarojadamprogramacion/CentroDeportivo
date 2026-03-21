package centrodeportivo.modelo;

import java.util.ArrayList;

public class Usuario {

	// Variable estática para autoincrementar el id automáticamente

	private static int contador = 0;
	private int idUsuario;
	private String nombreCompleto;
	private String telefono;
	private String contrasena;

	// Aquí aplicamos la relación que tiene un usuario

	private ArrayList<Reserva> reservas;

	public Usuario(String nombreCompleto, String telefono, String contrasena) {

		this.idUsuario = ++contador;
		this.nombreCompleto = nombreCompleto;
		this.telefono = telefono;
		this.contrasena = contrasena;

		// Inicializamos la lista de reservas vacía para que no dé error al añadir

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

	// Método para que el centro deportivo le asigne una reserva a este usuario

	public void addReserva(Reserva r) {
		if (r != null) {
			this.reservas.add(r);
		}
	}

	// Método para ver el historial

	public Reserva[] consultarHistorialUso() {
		return this.reservas.toArray(new Reserva[0]);
	}

	@Override
	public String toString() {
		return "\n¡Usuario creado con éxito! \n" + "ID: " + getIdUsuario() + "\n" + "Nombre: " + getNombreCompleto()
				+ "\n" + "Teléfono: " + getTelefono() + "\n";
	}
}