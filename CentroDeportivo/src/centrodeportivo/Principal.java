package centrodeportivo;
import java.util.Scanner;
import centrodeportivo.modelo.CentroDeportivo;
import centrodeportivo.modelo.Usuario;
import centrodeportivo.modelo.Instalacion;
import centrodeportivo.modelo.TipoInstalacion;
import centrodeportivo.modelo.Estado_Reserva;
import centrodeportivo.modelo.Tipo_Reserva;

public class Principal {
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		
	        CentroDeportivo centro = new CentroDeportivo();

	        do {
	            // Menú principal
	            System.out.println("\n¿Qué quieres hacer?");
	            System.out.println("1. Registrar usuario");
	            System.out.println("2. Ver instalaciones por ID");
	            System.out.println("3. Eliminar usuario");
	            System.out.println("4. Salir"); // opcional para poder terminar el bucle

	            int opcion = sc.nextInt();
	            sc.nextLine(); // consumir salto de línea

	            switch (opcion) {
	                case 1: // Registrar usuario
	                    System.out.print("Introduce nombre completo: ");
	                    String nombre = sc.nextLine();

	                    System.out.print("Introduce teléfono: ");
	                    String telefono = sc.nextLine();

	                    System.out.print("Introduce contraseña: ");
	                    String contrasena = sc.nextLine();

	                    Usuario nuevoUsuario = new Usuario(nombre, telefono, contrasena);
	                    centro.registrarUsuario(nuevoUsuario);

	                    System.out.println(nuevoUsuario); // usa toString() del usuario
	                    break;

	                case 2: // Ver instalaciones por ID
	                    System.out.println("\nInstalaciones ordenadas por ID:");
	                    Instalacion[] instalaciones = centro.getInstalacionesOrdenadasPorId();
	                    for (Instalacion i : instalaciones) {
	                        System.out.println(i.getIdInstalacion() + " - " + i.getNombre());
	                    }
	                    break;

	                case 3: // Eliminar usuario por nombre + contraseña
	                    System.out.print("Introduce tu nombre completo: ");
	                    String nombreEliminar = sc.nextLine();

	                    System.out.print("Introduce tu contraseña: ");
	                    String contrasenaEliminar = sc.nextLine();

	                    boolean eliminado = centro.eliminarUsuario(nombreEliminar, contrasenaEliminar);
	                    if (eliminado) {
	                        System.out.println("Usuario eliminado correctamente.");
	                    } else {
	                        System.out.println("Nombre o contraseña incorrectos. No se pudo eliminar.");
	                    }
	                    break;

	                case 4: // Salir
	                    System.out.println("Saliendo del sistema...");
	                    sc.close();
	                    System.exit(0); // termina el programa
	                    break;

	                default:
	                    System.out.println("Opción no válida, intenta de nuevo.");
	            }

	        } while (true); // bucle infinito hasta que el usuario elija salir
	    }
	}
	


