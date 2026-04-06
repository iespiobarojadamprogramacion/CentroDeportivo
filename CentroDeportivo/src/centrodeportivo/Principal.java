package centrodeportivo;

import java.util.Scanner;
import centrodeportivo.modelo.*;

public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); 

		// Llamamos al centro deportivo 
		
		CentroDeportivo centro = CentroDeportivo.getInstancia();

		// Variable para saber qué usuario está usando el programa ahora
		
		Usuario usuarioActual = null;

		System.out.println("=== Sistema de gestión deportiva (Grupo 3) ===");

		int opcion = 0;

		do {
			System.out.println("\n--- Menú Principal ---");
			System.out.println("1. Registrar un usuario (Paso previo para reservar)");
			System.out.println("2. Eliminar Usuario");
			System.out.println("3. Ver todas las Instalaciones");
			System.out.println("4. Consultar Tramos horarios libres");
			System.out.println("5. Ver Ocupación (Diaria o Semanal)");
			System.out.println("6. Crear una nueva Reserva");
			System.out.println("7. Modificar una Reserva existente");
			System.out.println("8. Cancelar una Reserva");
			System.out.println("9. Ver mi Historial y Reglas de uso");
			System.out.println("10. Salir");
			System.out.print("Seleccione una opción: ");

			// Evitamos que el programa falle si no introducen un número
			
			if (sc.hasNextInt()) {
				opcion = sc.nextInt();
				sc.nextLine();
			} else {
				System.out.println("Error: Introduce un número del 1 al 10.");
				sc.nextLine();
				continue;
			}

			switch (opcion) {
			case 1: // Registro
				System.out.print("Nombre completo: ");
				String nombre = sc.nextLine();
				System.out.print("Teléfono: ");
				String tel = sc.nextLine();
				System.out.print("Contraseña: ");
				String pass = sc.nextLine();

				// Creamos el objeto y lo registramos en el sistema
				
				usuarioActual = new Usuario(nombre, tel, pass);
				centro.registrarUsuario(usuarioActual);
				System.out.println("\n¡Usuario registrado con éxito!");
				break;

			case 2: // Eliminar
				System.out.print("Nombre: ");
				String nDel = sc.nextLine();
				System.out.print("Contraseña: ");
				String pDel = sc.nextLine();
				if (centro.eliminarUsuario(nDel, pDel)) {
					System.out.println("Usuario eliminado correctamente.");
					usuarioActual = null; // Reiniciamos la sesión
				} else {
					System.out.println("Error: Los datos no coinciden.");
				}
				break;

			case 3: // Ver instalaciones
				System.out.println("\n-- Instalaciones disponibles --");
				Instalacion[] lista = centro.getInstalacionesOrdenadasPorId();
				for (Instalacion i : lista) {
					System.out.println(i.getIdInstalacion() + ": " + i.getNombre());
				}
				break;

			case 4: // Tramos libres 
				Instalacion[] pistas = centro.getInstalacionesOrdenadasPorId();
				for (Instalacion p : pistas)
					System.out.println(p.getIdInstalacion() + " -> " + p.getNombre());
				System.out.print("ID Instalación (1 al 6): ");
				int idI = sc.nextInt();
				sc.nextLine();

				if (idI >= 1 && idI <= pistas.length) {
					System.out.print("Fecha (ej: 04/04/2026): ");
					String fLibre = sc.nextLine();
					String[] libres = centro.identificarTramosLibres(pistas[idI - 1], fLibre);
					System.out.println("Horas libres encontradas:");
					for (String s : libres)
						System.out.println("- " + s);
				} else {
					System.out.println("Error: El número debe estar entre 1 y " + pistas.length);
				}
				break;

			case 5: // Ocupación 
				System.out.println("1. Ocupación de un día / 2. Ocupación de 7 días");
				int tipoO = sc.nextInt();
				sc.nextLine();
				if (tipoO == 1) {
					System.out.print("Fecha a consultar: ");
					String fD = sc.nextLine();
					for (String s : centro.consultarOcupacionDiaria(fD))
						System.out.println(s);
				} else {
					System.out.println("Introduce las 7 fechas consecutivas:");
					String[] fS = new String[7];
					for (int j = 0; j < 7; j++)
						fS[j] = sc.nextLine();
					for (String s : centro.consultarOcupacionSemanal(fS))
						System.out.println(s);
				}
				break;

			case 6: // Crear reserva con polimorfismo
				if (usuarioActual == null) {
					System.out.println("Aviso: Debes registrarte primero en la opción 1.");
				} else {
					Instalacion[] ins = centro.getInstalacionesOrdenadasPorId();
					for (Instalacion p : ins)
						System.out.println(p.getIdInstalacion() + " -> " + p.getNombre());
					System.out.print("ID Instalación: ");
					int idSel = sc.nextInt();
					sc.nextLine();

					if (idSel >= 1 && idSel <= ins.length) {
						System.out.print("Fecha: ");
						String fR = sc.nextLine();
						System.out.print("Hora exacta (ej: 10:00 a 11:00): ");
						String hR = sc.nextLine();
						System.out.println("Tipo: INDIVIDUAL, GRUPAL o ACTIVIDAD_DIRIGIDA");
						String tR = sc.nextLine().toUpperCase();

						boolean ok = false;
						if (tR.equals("INDIVIDUAL")) {
							ok = centro.crearReserva(usuarioActual, ins[idSel - 1], fR, hR, "1h", Estado_Reserva.ACTIVA,
									null, null, 1, Tipo_Reserva.INDIVIDUAL);
						} else if (tR.equals("GRUPAL")) {
							System.out.print("¿Cuántas personas sois?: ");
							int num = sc.nextInt();
							sc.nextLine();
							ok = centro.crearReserva(usuarioActual, ins[idSel - 1], fR, hR, "1h", Estado_Reserva.ACTIVA,
									null, null, num, Tipo_Reserva.GRUPAL);
						} else if (tR.equals("ACTIVIDAD_DIRIGIDA")) {
							System.out.print("¿Qué actividad es? (ej: Yoga): ");
							String act = sc.nextLine();
							ok = centro.crearReserva(usuarioActual, ins[idSel - 1], fR, hR, "1h", Estado_Reserva.ACTIVA,
									"Monitor Asignado", act, 1, Tipo_Reserva.ACTIVIDAD_DIRIGIDA);
						}
						System.out.println(ok ? "¡Reserva realizada con éxito!"
								: "Fallo: Ya está ocupado o el horario no existe.");
					} else {
						System.out.println("Error: Ese ID de instalación no existe.");
					}
				}
				break;

			case 7: // Modificar reserva
			case 8: // Cancelar reserva
				if (usuarioActual == null) {
					System.out.println("Error: Primero registre un usuario.");
				} else {
					Reserva[] h = usuarioActual.consultarHistorialUso();
					if (h.length == 0) {
						System.out.println("No tienes ninguna reserva en tu historial.");
					} else {
						for (Reserva r : h)
							System.out.println("ID: " + r.getIdReserva() + " | Fecha: " + r.getFecha());
						System.out.print("Indica el ID de la reserva a gestionar: ");
						int idR = sc.nextInt();
						sc.nextLine();

						if (opcion == 7) {
							System.out.print("Nueva Fecha: ");
							String nf = sc.nextLine();
							System.out.print("Nueva Hora: ");
							String nh = sc.nextLine();
							// Usamos la instalación que ya tiene la reserva para el cambio
							centro.modificarReserva(idR, nf, nh, h[0].getInstalacion());
							System.out.println("Reserva modificada correctamente.");
						} else {
							centro.cancelarReserva(idR);
							System.out.println("Reserva cancelada correctamente.");
						}
					}
				}
				break;

			case 9: // Historial 
				if (usuarioActual == null) {
					System.out.println("No hay ningún usuario activo. Regístrate en la Opción 1.");
				} else {
					System.out.println("\n-- Historial de " + usuarioActual.getNombreCompleto() + " --");
					// Recorremos las reservas y llamamos al método polimórfico
					for (Reserva r : usuarioActual.consultarHistorialUso()) {
						System.out.println("Reserva ID " + r.getIdReserva() + ": " + r.getFecha() + " en "
								+ r.getInstalacion().getNombre());
						// Aquí se ve el polimorfismo, reglas según si es individual, grupal o dirigida
						System.out.println("   Reglas aplicadas: " + r.consultarReglasUso());
					}
				}
				break;

			case 10: // Salir
				System.out.println("Cerrando el sistema deportivo... ¡Hasta pronto!");
				break;

			default:
				System.out.println("Opción no válida, intenta de nuevo con un número del 1 al 10.");
			}
		} while (opcion != 10);
		sc.close();
	}
}
