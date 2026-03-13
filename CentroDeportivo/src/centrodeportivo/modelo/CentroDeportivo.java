package centrodeportivo.modelo;

import java.util.ArrayList;
import java.util.List;

public class CentroDeportivo {

	private String nombre;
	private int capacidad;
	private String horario;
	private String direccion;

	private List<Usuario> usuarios;
	private List<Instalacion> instalaciones;
	private List<Reserva> reservas;

	public CentroDeportivo(String nombre, int capacidad, String horario, String direccion) {

		this.nombre = nombre;
		this.capacidad = capacidad;
		this.horario = horario;
		this.direccion = direccion;

		this.usuarios = new ArrayList<>();
		this.instalaciones = new ArrayList<>();
		this.reservas = new ArrayList<>();

	}

	public String getNombre() {
		return nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getHorario() {
		return horario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void registrarUsuario(Usuario nuevoUsuario) {

		if (nuevoUsuario != null) {

			usuarios.add(nuevoUsuario);

		}
	}

	public Reserva[] visualizarReservasPorInstalacion(int idInstalacion, String fecha) {

		List<Reserva> encontradas = new ArrayList<>();

		for (Reserva r : reservas) {

			if (r.getFecha().equals(fecha)) {
				encontradas.add(r);
			}
		}

		return encontradas.toArray(new Reserva[0]);
	}

	public boolean registrarReserva(Usuario usuario, Instalacion instalacion, String fecha, String horaInicio) {

		if (usuario == null || instalacion == null) {
			return false;
		}

		if (instalacion.verificarDisponibilidad(fecha, horaInicio)) {

			System.out.println("Reserva registrada para " + usuario.getNombreCompleto());

			return true;

		}

		return false;

	}

	public String[] consultarOcupacionDiaria(String fecha) {

		String info = "Informe del día " + fecha + ": ";
		int contador = 0;

		for (Reserva r : reservas) {
			if (r.getFecha().equals(fecha)) {
				contador++;
			}
		}

		return new String[] { info + "Hay " + contador + " reservas confirmadas." };
	}

	public String[] consultarOcupacionSemanal(String fechaInicio) {

		return new String[] { "Ocupación semanal desde " + fechaInicio };
	}

	public String[] identificarTramosLibres(int idInstalacion, String fecha) {

		for (Instalacion inst : instalaciones) {

			if (inst.getIdInstalacion() == idInstalacion) {

				return new String[] { "09:00", "11:00", "15:00" };
			}
		}

		return new String[] { "Instalación no encontrada" };
	}

}
