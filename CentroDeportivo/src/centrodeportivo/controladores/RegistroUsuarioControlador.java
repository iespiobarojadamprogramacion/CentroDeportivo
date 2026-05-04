package centrodeportivo.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import centrodeportivo.modelo.APICentroDeportivo;
import centrodeportivo.vistas.RegistroUsuarioVista;

public class RegistroUsuarioControlador implements ActionListener {

	// Guardamos la pantalla que vamos a controlar
	
	private RegistroUsuarioVista vista;

	// Nos traemos la API única (Singleton) para hablar con los datos
	
	private APICentroDeportivo api = APICentroDeportivo.getInstance();

	// Al crear el controlador, le pasamos la vista para que se conozcan
	
	public RegistroUsuarioControlador(RegistroUsuarioVista vista) {
		this.vista = vista;
	}

	// Esto sera lo que va a suceder cuando alguien hace clic en el botón "Registrar"
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Se extraerán los textos que el usuario ha escrito en la pantalla
		
		String nombre = vista.getNombre();
		String telefono = vista.getTelefono();
		String contrasena = vista.getContrasena();

		// Comprobamos que no haya dejado nada en blanco
		
		if (nombre.isBlank() || telefono.isBlank() || contrasena.isBlank()) {
			vista.mostrarError("Por favor, rellena todos los campos.");
			return; // Cortamos aquí para que no siga haciendo cosas
		}

		// Le decimos a la API que intente registrarlo
		
		boolean registrado = api.registrarUsuario(nombre, telefono, contrasena);

		// Avisamos al usuario de si ha salido bien o mal
		
		if (registrado) {
			vista.mostrarExito("¡Usuario registrado correctamente!");
		} else {
			vista.mostrarError("Error al registrar. Quizás el usuario ya existe.");
		}
	}
}