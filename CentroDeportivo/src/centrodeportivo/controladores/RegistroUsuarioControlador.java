package centrodeportivo.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import centrodeportivo.modelo.APICentroDeportivo;
import centrodeportivo.vistas.RegistroUsuarioVista;
import utilidades.Util; 
import utilidades.ContrasenaInseguraException; 
import utilidades.NombreIncorrectoException;
import utilidades.TelefonoIncorrectoException;

public class RegistroUsuarioControlador implements ActionListener {

	// Guardamos la pantalla que vamos a controlar
	
	private RegistroUsuarioVista vista;

	// Nos traemos la API única (Singleton) para hablar con los datos
	
	private APICentroDeportivo api = APICentroDeportivo.getInstance();

	// Aqui al crear el controlador, le pasamos la vista para que se conozcan
	
	public RegistroUsuarioControlador(RegistroUsuarioVista vista) {
		this.vista = vista;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Lo primero que haremos sera extraer los textos que el usuario ha escrito en la pantalla
		
		String nombre = vista.getNombre();
		String telefono = vista.getTelefono();
		String contrasena = vista.getContrasena();

		// Comprobamos que no haya dejado nada en blanco 
		if (nombre.isBlank() || telefono.isBlank() || contrasena.isBlank()) {
			vista.mostrarError("Por favor, rellena todos los campos.");
			return;
		}
		
		// Verificamos que el nombre tenga una longitud mínima para evitar registros de una sola letra
		if (nombre.trim().length() < 3) {
			vista.mostrarError("El nombre debe tener al menos 3 caracteres.");
			return;
		}

		// Y empezaremos a validar todo paso a paso
		
		try {
			// Pasamos los datos por la clase Util para que busque fallos
			
			Util.validarNombre(nombre);
			Util.validarTelefono(telefono);
			Util.validarPassword(contrasena);

			// Si superó todas las pruebas sin saltar al catch, le decimos a la API que lo registre
			
			boolean registrado = api.registrarUsuario(nombre, telefono, contrasena);

			// Avisamos al usuario de si ha salido bien o si ya estaba repetido
			
			if (registrado) {
				vista.mostrarExito("¡Usuario registrado correctamente!");
			} else {
				vista.mostrarError("Error al registrar. Quizás el usuario ya existe en el sistema.");
			}

		} catch (NombreIncorrectoException nie) {
			// Si se detecta números en el nombre, el código contendra lo siguiente
			vista.mostrarError("Error: El nombre no puede contener números.");

		} catch (TelefonoIncorrectoException tie) {
			// Si el teléfono tiene letras o no tiene 9 números, mostrara este mensaje
			vista.mostrarError("Error: El teléfono debe tener exactamente 9 dígitos numéricos.");

		} catch (ContrasenaInseguraException cie) {
			// Y si pusieron "123" o una contraseña muy corta, lo atrapamos aquí
			vista.mostrarError("Error: La contraseña es muy corta (debe tener al menos 8 caracteres).");
		}
	}
}