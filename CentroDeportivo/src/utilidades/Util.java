package utilidades;

public class Util {
	
	public static void validarTelefono(String tel) throws TelefonoIncorrectoException{
		
		if (tel.isBlank()) {
			throw new TelefonoIncorrectoException();
		}else if(tel.length() !=9) {
			throw new TelefonoIncorrectoException();
		}
		try {
			Integer.valueOf(tel);
		}catch (NumberFormatException nfe) {
			throw new TelefonoIncorrectoException();
		}
	}
	
	public void validarPassword(String pass) throws ContraseñaInseguraException {

	    if (pass == null || pass.isBlank()) {
	        throw new ContraseñaInseguraException("La contraseña no puede estar vacía.");
	    }
	    
	    if (pass.length() >= 12 && 
	        pass.matches(".*[A-Z].*") && 
	        pass.matches(".*[a-z].*") && 
	        pass.matches(".*[0-9].*")) {
	        return; 
	    }
	    
	    if (pass.length() <= 7 && pass.matches("^[^0-9]*$")) {
	        throw new ContraseñaInseguraException("Contraseña corta y sin números.");
	    }
	   
	    try {
	        Integer.valueOf(pass);
	        throw new ContraseñaInseguraException("La contraseña no puede ser solo numérica.");
	    } catch (NumberFormatException nfe) {
	  }
	}
	
	
		
}
