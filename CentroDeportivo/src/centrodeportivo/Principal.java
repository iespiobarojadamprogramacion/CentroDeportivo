package centrodeportivo;

import java.util.Scanner;
import centrodeportivo.modelo.*; // Importamos todo el modelo para conectar las clases

public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Inicializamos el centro deportivo (el constructor ya crea las 6 instalaciones fijas)
		
		CentroDeportivo centro = new CentroDeportivo();

		// Está variable guarda al usuario que se registre para poder usarlo en las reservas
		
		Usuario usuarioActual = null;

		System.out.println("=== Sistema de gestión deportiva (Gupo 3) ===");

		int opcion = 0;

		do {
			System.out.println("\n--- Menú ---");
			System.out.println("1. Registrar un usuario (paso previo para reservar)");
			System.out.println("2. Ver listado de instalaciones");
			System.out.println("3. Crear una reserva individual");
			System.out.println("4. Consultar historial del usuario");
			System.out.println("5. Eliminar un usuario del sistema");
			System.out.println("6. Salir del programa");
			System.out.print("Seleccione una opción: ");

			opcion = sc.nextInt();
			sc.nextLine(); 

			switch (opcion) {
			case 1: // Registro del usuario 
				System.out.println("\n-- Formulario de Registro --");
				System.out.print("Nombre completo: ");
				String nombre = sc.nextLine();
				System.out.print("Teléfono: ");
				String tel = sc.nextLine();
				System.out.print("Contraseña: ");
				String pass = sc.nextLine();

				// Creamos el objeto y lo registramos en la lista central del centro deportivo
				
				usuarioActual = new Usuario(nombre, tel, pass);
				centro.registrarUsuario(usuarioActual);

				System.out.println("\n¡Usuario registrado con éxito!");
				System.out.println(usuarioActual.toString());
				break;

			case 2: // Visualización de instalaciones 
				System.out.println("\n-- Listado de instalaciones (ordenadas por id) --");
				Instalacion[] lista = centro.getInstalacionesOrdenadasPorId();
				for (int i = 0; i < lista.length; i++) {
					System.out.println(i + ". ID: " + lista[i].getIdInstalacion() + " - " + lista[i].getNombre());
				}
				break;

			case 3: // Verificamos que haya alguien "logueado" para poder asociar la reserva a un usuario
				if (usuarioActual == null) {
					System.out.println("Error: No hay un usuario activo. Debe registrarse en la Opción 1.");
				} else {
					System.out.println("\n-- Iniciando Reserva para: " + usuarioActual.getNombreCompleto() + " --");

					// Obtenemos las instalaciones para elegir una por su índice
	
					Instalacion[] pistas = centro.getInstalacionesOrdenadasPorId();
					System.out.println("Seleccione el número de la instalación deseada:");

					for (int i = 0; i < pistas.length; i++)
						System.out.println(i + " -> " + pistas[i].getNombre());

					int selec = sc.nextInt();
					sc.nextLine();

					if (selec >= 0 && selec < pistas.length) {
						System.out.print("Introduzca fecha (ej: 25/03/2026): ");
						String fecha = sc.nextLine();
						System.out.print("Introduzca horario (ej: 10:00 a 11:00): ");
						String hora = sc.nextLine();

						/* Vinculamos el objeto "usuarioActual" con la "pista" seleccionada. 
						 * El método "crearReserva" del centro valida la disponibilidad antes de confirmar.
						 */
						
						boolean exito = centro.crearReserva(usuarioActual, pistas[selec], fecha, hora, "1h",
								Estado_Reserva.ACTIVA, null, null, 1, Tipo_Reserva.INDIVIDUAL);

						if (exito) {
							System.out.println(
									"La reserva se ha guardado en el historial del usuario y de la instalación.");
						} else {
							System.out.println(
									"Fallo: Conflicto de disponibilidad. La pista ya está ocupada en ese horario.");
						}
					}
				}
				break;

			case 4: // Consulta el historial 
				if (usuarioActual == null) {
					System.out.println("Primero registre un usuario.");
				} else {
					System.out.println("\n-- Historial de reservas de " + usuarioActual.getNombreCompleto() + " --");
					Reserva[] h = usuarioActual.consultarHistorialUso();
					if (h.length == 0)
						System.out.println("No se han encontrado reservas registradas.");
					for (Reserva r : h) {
						System.out.println("- Fecha: " + r.getFecha() + " | Hora: " + r.getHoraInicio() + " | Lugar: "
								+ r.getInstalacion().getNombre());
					}
				}
				break;

			case 5: // Eliminar un usuario y resetea la sesión 
				System.out.print("Nombre del usuario a eliminar: ");
				String n = sc.nextLine();
				System.out.print("Contraseña de seguridad: ");
				String p = sc.nextLine();

				if (centro.eliminarUsuario(n, p)) {
					System.out.println("Usuario eliminado correctamente del registro central.");

					/*
					 * * Para resetear la sesión ponemos "usuarioActual" a null para invalidar la sesión
					 * actual. Esto impide que se sigan haciendo reservas a nombre de un usuario borrado.
					 */
					
					usuarioActual = null;
				} else {
					System.out.println("Error: Los datos no coinciden. No se pudo completar la operación.");
				}
				break;

			case 6: // Salir
				System.out.println("Cerrando aplicación...");
				break;

			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}
		} while (opcion != 6);

		sc.close();
	}
}