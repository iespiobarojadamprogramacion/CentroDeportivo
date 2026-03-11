package centrodeportivo.modelo;

public class Usuario {
	
	private static int contador=0;
	private int idUsuario;
	private String nombreCompleto;
	private String telefono;
	private String contrasena;
	
	public Usuario (int idUsuario,String nombreCompleto,String telefono,String contrasena) {
		this.idUsuario += contador;
		this.nombreCompleto=nombreCompleto;
		this.telefono=telefono;
		this.contrasena=contrasena;
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

	
}
