package centrodeportivo.modelo;

import java.util.ArrayList;

public class Usuario {

	// Variable estática para generar el ID autoincremental automáticamente 
	
	private static int contador = 0;

	private int idUsuario;
	private String nombreCompleto;
	private String telefono;
	private String contrasena;

	// Lista que almacena las reservas asociadas a este usuario (0..*)
	
	private ArrayList<Reserva> reservas;

	// Constructor que inicializa los datos del usuario y genera su ID
	
	public Usuario(String nombreCompleto, String telefono, String contrasena) {
		this.idUsuario = ++contador;
		this.nombreCompleto = nombreCompleto;
		this.telefono = telefono;
		this.contrasena = contrasena;

		// Inicializamos la lista de reservas vacía para evitar errores al añadirlo luego
		
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

	// Añade una nueva reserva al historial del usuario si no es nula
	
	public void addReserva(Reserva r) {
		if (r != null) {
			this.reservas.add(r);
		}
	}

	// Devuelve el historial completo convirtiendo la lista a un array de tipo Reserva[]
	
	public Reserva[] consultarHistorialUso() {
		return this.reservas.toArray(new Reserva[0]);
	}

	// Formato claro para mostrar los datos del usuario por consola
	
	@Override
	public String toString() {
		return "--- DATOS DEL USUARIO ---\n" + "ID: " + getIdUsuario() + "\n" + "Nombre: " + getNombreCompleto() + "\n"
				+ "Teléfono: " + getTelefono() + "\n" + "-------------------------";
	}
}
