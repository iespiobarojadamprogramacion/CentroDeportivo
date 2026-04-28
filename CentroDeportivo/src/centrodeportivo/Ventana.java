package centrodeportivo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import centrodeportivo.modelo.*;
import vistas.*;

public class Ventana extends JFrame {

	public static Usuario usuarioActual;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private CardLayout cardLayout;
	private JPanel panelContenedor;

	public Ventana() {
		setTitle("Centro Deportivo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// CardLayout
		cardLayout = new CardLayout();
		panelContenedor = new JPanel(cardLayout);
		contentPane.add(panelContenedor, BorderLayout.CENTER);

		// Añadir paneles
		panelContenedor.add(new PanelInicio(), "inicio");
		
		panelContenedor.add(new PanelRegistro(), "registro");
		panelContenedor.add(new PanelEliminarUsuario(), "eliminar");
		panelContenedor.add(new PanelInstalaciones(), "instalaciones");
		panelContenedor.add(new PanelTramosLibres(), "tramos");
		panelContenedor.add(new PanelOcupacion(), "ocupacion");
		panelContenedor.add(new PanelCrearReserva(), "crear");
		panelContenedor.add(new PanelModificarReserva(), "modificar");
		panelContenedor.add(new PanelCancelarReserva(), "cancelar");
		panelContenedor.add(new PanelHistorial(), "historial");
		
		

		
		crearMenu();
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("Menú");
		menuBar.add(menu);

		JMenuItem itemInicio = new JMenuItem("Inicio");
		
		JMenuItem itemRegistro = new JMenuItem("Registrar Usuario");
		JMenuItem itemEliminar = new JMenuItem("Eliminar Usuario");
		JMenuItem itemInst = new JMenuItem("Ver Instalaciones");
		JMenuItem itemTramos = new JMenuItem("Tramos Libres");
		JMenuItem itemOcupacion = new JMenuItem("Ocupación");
		JMenuItem itemCrear = new JMenuItem("Crear Reserva");
		JMenuItem itemModificar = new JMenuItem("Modificar Reserva");
		JMenuItem itemCancelar = new JMenuItem("Cancelar Reserva");
		JMenuItem itemHistorial = new JMenuItem("Historial");

		menu.add(itemInicio);
		
		menu.add(itemRegistro);
		menu.add(itemEliminar);
		menu.add(itemInst);
		menu.add(itemTramos);
		menu.add(itemOcupacion);
		menu.add(itemCrear);
		menu.add(itemModificar);
		menu.add(itemCancelar);
		menu.add(itemHistorial);
		

		// Eventos
		itemRegistro.addActionListener(e -> cardLayout.show(panelContenedor, "registro"));
		itemEliminar.addActionListener(e -> cardLayout.show(panelContenedor, "eliminar"));
		itemInst.addActionListener(e -> cardLayout.show(panelContenedor, "instalaciones"));
		itemTramos.addActionListener(e -> cardLayout.show(panelContenedor, "tramos"));
		itemOcupacion.addActionListener(e -> cardLayout.show(panelContenedor, "ocupacion"));
		itemCrear.addActionListener(e -> cardLayout.show(panelContenedor, "crear"));
		itemModificar.addActionListener(e -> cardLayout.show(panelContenedor, "modificar"));
		itemCancelar.addActionListener(e -> cardLayout.show(panelContenedor, "cancelar"));
		itemHistorial.addActionListener(e -> cardLayout.show(panelContenedor, "historial"));

	}
}
