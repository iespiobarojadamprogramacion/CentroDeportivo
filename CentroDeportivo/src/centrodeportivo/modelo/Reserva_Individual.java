package centrodeportivo.modelo;

// Clase hija que representa una reserva para uso individual 

public class Reserva_Individual extends Reserva {

	// Constructor que llama a la clase padre (Reserva) usando super()
	
	public Reserva_Individual(String fecha, String horaInicio, String duracion, Estado_Reserva estado, Usuario usuario,
			Instalacion instalacion) {
		super(fecha, horaInicio, duracion, estado, usuario, instalacion);
	}

	// Sobreescribimos el método abstracto del padre con las reglas específicas
	
	@Override
	public String consultarReglasUso() {
		return "Reglas para Reserva Individual:\n"
				+ "1. La impuntualidad hará que no se le reembolse la reserva (tarde a partir de 15 min).\n"
				+ "2. El reservante es responsable del buen uso de la instalación.\n"
				+ "3. Debe abandonar la pista puntualmente al finalizar su tiempo.\n"
				+ "Cualquier duda, contacte con recepción.";
	}
}
