package vistas;

import javax.swing.*;

import centrodeportivo.Ventana;
import centrodeportivo.modelo.*;

public class PanelEliminarUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CentroDeportivo centro = CentroDeportivo.getInstancia();

    public PanelEliminarUsuario() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField nombre = new JTextField();
        UIUtil.estilizarCampo(nombre);
        
        JTextField pass = new JTextField();
        UIUtil.estilizarCampo(pass);

        add(new JLabel("Nombre Completo:"));
        add(nombre);

        add(new JLabel("Contraseña:"));
        add(pass);

        JButton btn = new JButton("Eliminar usuario");
        add(btn);
        
        btn.addActionListener(e -> {
            boolean ok = centro.eliminarUsuario(nombre.getText(), pass.getText());
            JOptionPane.showMessageDialog(this,
                    ok ? "Usuario eliminado" : "Error: datos incorrectos");
            
            if (Ventana.usuarioActual != null &&
            	    Ventana.usuarioActual.getNombreCompleto().equals(nombre.getText())) {
            	    Ventana.usuarioActual = null;
            	}
        });
    }
}
