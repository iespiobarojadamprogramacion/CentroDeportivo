package utilidades;

public class Util {

	// Validaremos que el teléfono sea correcto 
	
	public static void validarTelefono(String tel) throws TelefonoIncorrectoException {

		// Si el texto está vacío o no tiene exactamente 9 números, saltamos al error
		
		if (tel.isBlank() || tel.length() != 9) {
			throw new TelefonoIncorrectoException();
		}

		// Intentamos convertir el texto a número, pues si alguien escribió letras, el Integer.valueOf fallará
		
		try {
			Integer.valueOf(tel);
		} catch (NumberFormatException nfe) {
			// Si falla la conversión, lanzamos nuestro propio error de teléfono
			throw new TelefonoIncorrectoException();
		}
	}
	
	
	// Validamos que la fecha tenga el tamaño correcto y sean números
	
		public static void validarFecha(String fecha) throws FechaIncorrectaException {
			
			// Si no tiene exactamente 10 letras (ej: 12/05/2024), estaria mal
			
			if (fecha.length() != 10) {
				throw new FechaIncorrectaException();
			}
			
			try {
				// Partimos el texto y comprobamos que el día, mes y año sean números de verdad
				Integer.valueOf(fecha.substring(0, 2)); // El día
				Integer.valueOf(fecha.substring(3, 5)); // El mes
				Integer.valueOf(fecha.substring(6, 10)); // Y el año
			} catch (NumberFormatException e) {
				// Y si falla al convertir, significa que pusieron letras, por lo que lanzariamos un error
				throw new FechaIncorrectaException();
			}
		}

	// Validaremos la contraseña 
	
	public static void validarPassword(String pass) throws ContrasenaInseguraException {

		// Si está en blanco o tiene menos de 8 caracteres, es muy insegura, así que mostraremos un error
		
		if (pass == null || pass.isBlank() || pass.length() < 8) {
			throw new ContrasenaInseguraException();
		}

	}

	// Validamos que el nombre no contenga números 
	
	public static void validarNombre(String nombre) throws NombreIncorrectoException {

		// Si nos pasan un nombre en blanco, estaria mal
		
		if (nombre == null || nombre.isBlank()) {
			throw new NombreIncorrectoException();
		}

		// Recorremos el nombre letra por letra 
		
		for (int i = 0; i < nombre.length(); i++) {
			char letra = nombre.charAt(i); // Sacaremos la letra de la posición 'i'

			// Y comprobaremos si esa letra es en realidad un número
			if (Character.isDigit(letra)) {
				// Si detectamos un solo número, lanzamos el error y paramos todo
				throw new NombreIncorrectoException();
			}
		}
	}
}