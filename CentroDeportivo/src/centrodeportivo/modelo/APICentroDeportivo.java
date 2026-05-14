package centrodeportivo.modelo;

import java.util.ArrayList;

public class APICentroDeportivo {

    // Guardamos la única API que vamos a usar en todo el programa (Patrón Singleton)
	
    private static APICentroDeportivo api;
    
    // Esta es la conexión directa con la clase que tiene todas las listas y datos
    
    private CentroDeportivo centro;

    // Constructor privado para que nadie pueda crear otra API usando "new" desde fuera
    
    private APICentroDeportivo() {
        // Al arrancar, cargamos los datos del centro deportivo
        centro = CentroDeportivo.getInstancia();
    }

    // Método para conseguir la API desde cualquier parte del código
    
    public static APICentroDeportivo getInstance() {
        // Y si todavía no se ha creado, la creamos por primera vez
        if (api == null) {
            api = new APICentroDeportivo();
        }
        // Devolvemos la API lista para usarse
        return api;
    }

    // Esto recibe los textos de la vista, crea el objeto y lo manda a guardar
    
    public boolean registrarUsuario(String nombre, String telefono, String contrasena) {
        Usuario nuevo = new Usuario(nombre, telefono, contrasena);
        return centro.registrarUsuario(nuevo);
    }

    // Guardara los datos de las instalaciones en una tabla de textos para mostrarlos en la pantalla
    
    public String[][] consultarInstalaciones() {
        // Pediremos también la lista de instalaciones ordenada
        Instalacion[] instalaciones = centro.getInstalacionesOrdenadasPorId();
        
     // Preparamos una tabla vacía en donde tendrá todas las filas de las instalaciones que hay, y 3 columnas para sus datos
        
        String[][] resultado = new String[instalaciones.length][3];
        
        // Rellenamos fila por fila con los datos
        
        for (int i = 0; i < instalaciones.length; i++) {
            resultado[i][0] = String.valueOf(instalaciones[i].getIdInstalacion()); // ID
            resultado[i][1] = instalaciones[i].getNombre(); // Nombre
            resultado[i][2] = String.valueOf(instalaciones[i].getTipo()); // Tipo
        }
        return resultado;
    }

    // Cancelamos la reserva usando su ID (el número ya llega convertido desde el controlador)
    
    public boolean cancelarReserva(int idReserva) {
        return centro.cancelarReserva(idReserva);
    }

    // Se pide cuántas reservas hay en un día en concreto
    
    public String[] consultarOcupacion(String fecha) {
        return centro.consultarOcupacionDiaria(fecha);
    }

    // Borra al usuario solo si el nombre y la contraseña coinciden
    
    public boolean eliminarUsuario(String nombre, String contrasena) {
        return centro.eliminarUsuario(nombre, contrasena);
    }

    // Intenta hacer la reserva con los datos que ya validó el controlador
    
    public boolean crearReservaCompleta(String nombreUsuario, int idIns, String fecha, String hora, Tipo_Reserva tipoEnum,
            int participantes, String actividad) {
        
        Usuario user = null;
        // Se busca en la lista si el usuario existe de verdad
        for (Usuario u : centro.getUsuarios()) {
            if (u.getNombreCompleto().equalsIgnoreCase(nombreUsuario)) {
                user = u;
                break; // Y si lo encontramos dejamos de buscar
            }
        }
        
        Instalacion ins = null;
        // Aqui buscamos si la instalación existe usando el ID que nos pasaron
        Instalacion[] listaIns = centro.getInstalacionesOrdenadasPorId();
        for (int i = 0; i < listaIns.length; i++) {
            if (listaIns[i].getIdInstalacion() == idIns) {
                ins = listaIns[i];
                break; 
            }
        }

        // Si encontramos al usuario y a la instalación, haremos la reserva
        
        if (user != null && ins != null) {
            // Le pasamos todo al centro, donde le pondremos valores fijos a la duración y al monitor por ahora
            return centro.crearReserva(user, ins, fecha, hora, "1h", Estado_Reserva.ACTIVA, "Sin monitor", actividad, participantes, tipoEnum);
        }
        
     // Si no encontramos al usuario o a la instalación, devolvemos falso para avisar
        return false;
    }
}