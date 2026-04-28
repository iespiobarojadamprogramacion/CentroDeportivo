package vistas;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import centrodeportivo.Ventana;
import centrodeportivo.modelo.*;


public class PanelRegistro extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CentroDeportivo centro;

	public PanelRegistro() {
		this.centro = CentroDeportivo.getInstancia();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(new JLabel("Nombre Completo:"));
		JTextField nombre = new JTextField();
		UIUtil.estilizarCampo(nombre);
		add(nombre);

		add(new JLabel("Teléfono:"));
		JTextField tel = new JTextField();
		UIUtil.estilizarCampo(tel);
		add(tel);

		add(new JLabel("Contraseña:"));
		JTextField pass = new JTextField();
		UIUtil.estilizarCampo(pass);
		add(pass);

		JButton btn = new JButton("Registrar");
		add(btn);

		btn.addActionListener(e -> {
			Usuario u = new Usuario(
				    nombre.getText(),
				    tel.getText(),
				    pass.getText()
				);

				boolean ok = centro.registrarUsuario(u);

				if (ok) {
				    Ventana.usuarioActual = u;
				    JOptionPane.showMessageDialog(this, "Usuario registrado y logueado");
				} else {
				    JOptionPane.showMessageDialog(this, "Error: usuario inválido o duplicado");
				}


		});
	}
}