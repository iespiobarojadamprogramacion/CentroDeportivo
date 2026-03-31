package centrodeportivo;

import java.util.Scanner;
import centrodeportivo.modelo.*;


public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		CentroDeportivo centro = CentroDeportivo.getInstancia();

		// Esta variable guarda al usuario que se registre para poder usarlo en las
		// reservas

		Usuario usuarioActual = null;

		System.out.println("=== Sistema de gestión deportiva (Grupo 3) ===");

		int opcion = 0;

		do {
			System.out.println("\n--- Menú ---");
			System.out.println("1. Registrar un usuario (paso previo para reservar)");
			System.out.println("2. ELimianr Usuario");
			System.out.println("3. Ver Instalaciones");
			System.out.println("4. Ver Tramos libres");
			System.out.println("5. Ver Ocupacion");
			System.out.println("6. Crear Reserva");
			System.out.println("7. Modificar Reserva");
			System.out.println("8. Cancelar Reserva");
			System.out.println("9. Historial");
			System.out.println("10. Salir");
			System.out.print("Seleccione una opción: ");

			// Validación básica para evitar que el programa se dañe si no ingresan un
			// número

			if (sc.hasNextInt()) {
				opcion = sc.nextInt();
				sc.nextLine();
			} else {
				System.out.println("Por favor, introduce un número válido.");
				sc.nextLine();
				continue;
			}

			switch (opcion) {
			case 1: // Registro del usuario
				System.out.println("\n-- Formulario de Registro --");
				System.out.print("Nombre completo: ");
				String nombre = sc.nextLine();
				System.out.print("Teléfono: ");
				String tel = sc.nextLine();
				System.out.print("Contraseña: ");
				String pass = sc.nextLine();

				// Creamos el objeto y lo registramos

				usuarioActual = new Usuario(nombre, tel, pass);
				centro.registrarUsuario(usuarioActual);

				System.out.println("\n¡Usuario registrado con éxito!");
				System.out.println(usuarioActual.toString());
				break;

			case 2:// Eliminar usuario
				System.out.print("Nombre del usuario a eliminar: ");
				String n = sc.nextLine();
				System.out.print("Contraseña de seguridad: ");
				String p = sc.nextLine();

				if (centro.eliminarUsuario(n, p)) {
					System.out.println("Usuario eliminado correctamente.");
					usuarioActual = null; // Reseteamos la sesión
				} else {
					System.out.println("Error: Los datos no coinciden.");
				}
				break;
			case 3:// Visualización de instalaciones
				System.out.println("\n-- Listado de instalaciones (ordenadas por id) --");
				Instalacion[] lista = centro.getInstalacionesOrdenadasPorId();
				for (int i = 0; i < lista.length; i++) {
					System.out.println(i + ". ID: " + lista[i].getIdInstalacion() + " - " + lista[i].getNombre());
				}
				break;

			case 4:// VEr tramos libres
				
				System.out.println("De que instalción desea ver sus tramos libres");
				
				Instalacion[] pistas = centro.getInstalacionesOrdenadasPorId();

				System.out.println("Seleccione el número de la instalación deseada:");

				for (int i = 0; i < pistas.length; i++) {
					System.out.println(i + " -> " + pistas[i].getNombre());
				}
				
				int selec = sc.nextInt();

				sc.nextLine();
				
				System.out.println("de que tramo?");
				
				System.out.println("Los horarios de la Instalacion elegida son: " +
						"\n" + pistas[selec].getHorarioDisponibilidad());

						String fecha = sc.nextLine();
				
				String[] resultado = centro.identificarTramosLibres(pistas[selec], fecha);

				System.out.println("Los tramos libres para reservar son: ");
				
				for (String r : resultado) {
				    System.out.println(r);
				}
				break;
				
			case 5://Ver Ocupación
				
				System.out.println("¿Que ocupación desea ver?");
				System.out.println("1. Ocupacion Diaria");
				System.out.println("2. Ocupacion Semanal");
				
				String ocupacion_pedida = sc.nextLine();
				
				if(ocupacion_pedida.equalsIgnoreCase("Ocupacion Diaria")) {
					
					System.out.println("¿De que fecha quiere consultar la ocupación?");
					System.out.println("Use el siguiente formato siempre. ej: 01/04/26");
					
					String fechas = sc.nextLine();
					
					centro.consultarOcupacionDiaria(fechas);
					
				}else if(ocupacion_pedida.equalsIgnoreCase("Ocupacion Semanal")) {
					
					System.out.println("¿Dime los 7 dias que va a consultar");
					System.out.println("Use el siguiente formato siempre. ej: 01/04/26");
					
					String [] fechas = {sc.nextLine(),sc.nextLine(),sc.nextLine(),
							sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine()};
					
					centro.consultarOcupacionSemanal(fechas);
					
					
				}else {
					System.out.println("No existe esa ocupación, intentelo más tarde.");
				}
				
				break;
			case 6: // Reservas

				if (usuarioActual == null) {

					System.out.println("Error: No hay un usuario activo. Debe registrarse en la Opción 1.");

				} else {

					System.out.println("\n-- Iniciando Reserva para: " + usuarioActual.getNombreCompleto() + " --");

					Instalacion[] insta = centro.getInstalacionesOrdenadasPorId();

					System.out.println("Seleccione el número de la instalación deseada:");

					for (int i = 0; i < insta.length; i++) {
						System.out.println(i + " -> " + insta[i].getNombre());
					}
					
					int slece = sc.nextInt();

					sc.nextLine();


					if (slece >= 0 && slece < insta.length) {

						System.out.print("Introduzca fecha (ej: 25/03/2026): ");

						String fechas = sc.nextLine();

						System.out.print("Introduzca horario (ej: 10:00 a 11:00): \n");
						
						System.out.println("Los horarios de la Instalacion elegida son: " +
						"\n" + insta[slece].getHorarioDisponibilidad());

						String hora = sc.nextLine();

						// Bucle para llamar al método del centro que usa polimorfismo internamente

						System.out.println("¿Que tipo de reserva es? (ESCRIBELO) ");

						System.out.println("1. INDIVIDUAL"+"\n"+
										   "2. GRUPAL"+"\n"+
										   "3. ACTIVIDAD_DIRIGIDA"+ "\n");

						String tipo = sc.nextLine().toUpperCase();

						if(tipo.equals("INDIVIDUAL")) {

						//Reserva Individual

						boolean exito = centro.crearReserva(usuarioActual, insta[slece], fechas, hora, "1h",
								Estado_Reserva.ACTIVA, null, null, 1, Tipo_Reserva.INDIVIDUAL);
						
						if (exito) {
							System.out.println("¡Reserva confirmada y guardada en el historial!");
						} else {
							System.out.println("Fallo: La pista ya está ocupada o el horario no existe.");
						}

					}else if (tipo.equals("GRUPAL")) {

						System.out.println("Cuantos sois?");

						int num = sc.nextInt();

						boolean exito = centro.crearReserva(usuarioActual, insta[slece], fechas, hora, "1h",
								Estado_Reserva.ACTIVA, null, null, num, Tipo_Reserva.GRUPAL);

						if (exito) {
							System.out.println("¡Reserva confirmada y guardada en el historial!");
						} else {
							System.out.println("Fallo: La pista ya está ocupada o el horario no existe.");
						}

					}else if(tipo.equals("ACTIVIDAD_DIRIGIDA")) {

						String[] monitores = { "Juan", "Ana", "Carlos", "Lucas", "Martin", "Alejandra", "Maria" };

						int num = (int) (Math.random() * monitores.length);

						String monitor = monitores[num];

						System.out.println("¿Cual es tu Actividad dirigida?");

						String actividad = sc.nextLine();

						boolean exito = centro.crearReserva(usuarioActual, insta[slece], fechas, hora, "1h",
								Estado_Reserva.ACTIVA, monitor, actividad, 1, Tipo_Reserva.ACTIVIDAD_DIRIGIDA);
					
						if (exito) {
							System.out.println("¡Reserva confirmada y guardada en el historial!");
						} else {
							System.out.println("Fallo: La pista ya está ocupada o el horario no existe.");
						}
					  }
					}
				}

				break;
			case 7: //Modificar reserva
				System.out.println("Dime el Id de la Reserva que quieres modificar");
				
				if (usuarioActual == null) {
					System.out.println("Primero registre un usuario.");
				} else {
					System.out.println("\n-- Historial de reservas de " + usuarioActual.getNombreCompleto() + " --");
					Reserva[] h = usuarioActual.consultarHistorialUso();
					if (h.length == 0) {
						System.out.println("No se han encontrado reservas registradas.");
					} else {
						for (Reserva r : h) {
							System.out.println("- ID Reserva: " + r.getIdReserva() + " Fecha: " + 
						r.getFecha() + " | Hora: " + r.getHoraInicio()+ " | Lugar: " + r.getInstalacion().getNombre());
						}
					}
				}
				
				System.out.println(" \n Cual es el id de la reserva que quiere cambiar \n");
				
				int Id_reserva = sc.nextInt();
				sc.nextLine();
				
				Instalacion[] inst = centro.getInstalacionesOrdenadasPorId();

				System.out.println("Seleccione el número de la instalación nueva: ");

				for (int i = 0; i < inst.length; i++) {
					System.out.println(i + " -> " + inst[i].getNombre());
				}
				
				int sleec = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Cual es la nueva fecha de la reserva"+"\n");
				
				String fechaa = sc.nextLine();
				
				System.out.println("Cual es la nueva hora de la reserva"+"\n");
				
				System.out.println("Las horas reservables de esa Instalacion son: ");
				
				inst[sleec].getHorarioDisponibilidad();
		
				String[] resultados = centro.identificarTramosLibres(inst[sleec], fechaa);

				System.out.println("Los tramos libres para reservar son: ");
		
				for (String r : resultados) {
					System.out.println(r);
				}
				
				String hora = sc.nextLine();
				
				centro.modificarReserva(Id_reserva, fechaa, hora, inst[sleec]);
				
				break;
				
			case 8://Cancelar reserva
				System.out.println("Dime el Id de la Reserva que quieres modificar");
				
				if (usuarioActual == null) {
					System.out.println("Primero registre un usuario.");
				} else {
					System.out.println("\n-- Historial de reservas de " + usuarioActual.getNombreCompleto() + " --");
					Reserva[] h = usuarioActual.consultarHistorialUso();
					if (h.length == 0) {
						System.out.println("No se han encontrado reservas registradas.");
					} else {
						for (Reserva r : h) {
							System.out.println("- ID Reserva: " + r.getIdReserva() + " Fecha: " + 
						r.getFecha() + " | Hora: " + r.getHoraInicio()+ " | Lugar: " + r.getInstalacion().getNombre());
						}
					}
				}
				
				System.out.println(" \n Cual es el id de la reserva que quiere cancelar \n");
				
				int Id = sc.nextInt();
				
				sc.nextLine();
				centro.cancelarReserva(Id);
				
				break; 
			case 9:// Historial
				if (usuarioActual == null) {
					System.out.println("Primero registre un usuario.");
				} else {
					System.out.println("\n-- Historial de reservas de " + usuarioActual.getNombreCompleto() + " --");
					Reserva[] h = usuarioActual.consultarHistorialUso();
					if (h.length == 0) {
						System.out.println("No se han encontrado reservas registradas.");
					} else {
						for (Reserva r : h) {
							System.out.println("- Fecha: " + r.getFecha() + " | Hora: " + r.getHoraInicio()
									+ " | Lugar: " + r.getInstalacion().getNombre());
							// Aquí se ve el polimorfismo en acción
							System.out.println("  Reglas: " + r.consultarReglasUso() + "\n");
						}
					}
				}
				break;
			case 10: // Salir
				System.out.println("Cerrando aplicación...");
				break;

			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 10);

		sc.close();
	}
}