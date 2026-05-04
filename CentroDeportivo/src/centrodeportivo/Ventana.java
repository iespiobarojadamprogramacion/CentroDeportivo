package centrodeportivo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import centrodeportivo.vistas.CancelarReservaVista;
import centrodeportivo.vistas.ConsultaInstalacionesVista;
import centrodeportivo.vistas.ConsultaOcupacionVista;
import centrodeportivo.vistas.CrearReservaVista;
import centrodeportivo.vistas.RegistroUsuarioVista;

// Añadimos ActionListener para que la ventana sepa cuándo le hacemos clic a algo

public class Ventana extends JFrame implements ActionListener {

	// Preparamos las variables para las opciones del menú desplegable
	
	private JMenuItem registroJMenuItem;
	private JMenuItem crearReservaJMenuItem;
	private JMenuItem cancelarReservaJMenuItem;
	private JMenuItem instalacionesJMenuItem;
	private JMenuItem ocupacionJMenuItem;

	public Ventana() {
		
		// Le ponemos el título a la ventana principal
		
		super("Gestión Centro Deportivo");

		// Le damos el tamaño y le decimos dónde tiene que aparecer al abrirse
		
		setBounds(100, 100, 600, 500);

		// Esto es importante para que el programa se cierre del todo al darle a la 'X'
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Quitamos los diseños automáticos para poder poner las cosas exactamente donde queremos
		
		setLayout(null);

		// Creamos la barra gris de arriba del todo
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Le ponemos una pestaña que se llame "Gestión"
		
		JMenu gestionJMenu = new JMenu("Gestión");
		menuBar.add(gestionJMenu);

		// Y ahora empezaremos a rellenar el menú con las 5 opciones

		// Opción 1: Creamos el botón, lo metemos en el menú y le decimos que esté atento al clic (this)
		
		registroJMenuItem = new JMenuItem("Registrar Usuario");
		gestionJMenu.add(registroJMenuItem);
		registroJMenuItem.addActionListener(this);

		// Opción 2
		
		crearReservaJMenuItem = new JMenuItem("Crear Reserva");
		gestionJMenu.add(crearReservaJMenuItem);
		crearReservaJMenuItem.addActionListener(this);

		// Opción 3
		
		cancelarReservaJMenuItem = new JMenuItem("Cancelar Reserva");
		gestionJMenu.add(cancelarReservaJMenuItem);
		cancelarReservaJMenuItem.addActionListener(this);

		// Opción 4
		
		instalacionesJMenuItem = new JMenuItem("Consultar Instalaciones");
		gestionJMenu.add(instalacionesJMenuItem);
		instalacionesJMenuItem.addActionListener(this);

		// Opción 5
		
		ocupacionJMenuItem = new JMenuItem("Consultar Ocupación");
		gestionJMenu.add(ocupacionJMenuItem);
		ocupacionJMenuItem.addActionListener(this);

		// Hacemos que la ventana sea visible al arrancar
		
		setVisible(true);
	}

	// Este método saltara automáticamente cuando el usuario toque alguna opción del menú
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Comprobamos qué botón exacto ha tocado el usuario para cargar su pantalla

		if (e.getSource() == registroJMenuItem) {
			System.out.println("Cargando vista Registro...");
			RegistroUsuarioVista vista = new RegistroUsuarioVista();
			setContentPane(vista);			
		}
		else if (e.getSource() == crearReservaJMenuItem) {
			System.out.println("Cargando vista Crear Reserva...");
			CrearReservaVista vista = new CrearReservaVista();
			setContentPane(vista);
		}
		else if (e.getSource() == cancelarReservaJMenuItem) {
			System.out.println("Cargando vista Cancelar Reserva...");
			CancelarReservaVista vista = new CancelarReservaVista();
			setContentPane(vista);
		}
		else if (e.getSource() == instalacionesJMenuItem) {
			System.out.println("Cargando vista Instalaciones...");	
			ConsultaInstalacionesVista vista = new ConsultaInstalacionesVista();
			setContentPane(vista);
		}
		else if (e.getSource() == ocupacionJMenuItem) {
			System.out.println("Cargando vista Ocupación...");	
			ConsultaOcupacionVista vista = new ConsultaOcupacionVista();
			setContentPane(vista);
		}

		// Refrescamos la ventana por dentro para borrar lo anterior y mostrar lo nuevo
		
		revalidate();
		repaint();
		
	}
}