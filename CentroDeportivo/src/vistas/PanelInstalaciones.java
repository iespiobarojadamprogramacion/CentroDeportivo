package vistas;

	import javax.swing.*;
	import centrodeportivo.modelo.*;

	public class PanelInstalaciones extends JPanel {
		private static final long serialVersionUID = 1L;

		private CentroDeportivo centro;

		public PanelInstalaciones() {
			this.centro = CentroDeportivo.getInstancia();
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			JButton btn = new JButton("Cargar Instalaciones");
			add(btn);

			JTextArea area = new JTextArea();
			UIUtil.estilizarArea(area);
			
			JScrollPane scroll = new JScrollPane(area);
			UIUtil.estilizarScroll(scroll);
			add(scroll);

			btn.addActionListener(e -> {
				area.setText("");
				for (Instalacion i : centro.getInstalacionesOrdenadasPorId()) {
					area.append(i.getIdInstalacion() + ": " + i.getNombre() + "\n");
				}
			});
		}
		

	}