package Form;

public class Comentario {
	String nombrePersona,emailPersona,mensajePersona;
	
	public Comentario(String nom, String em, String msj) {
		nombrePersona=nom;
		emailPersona= em;
		mensajePersona= msj;
	};
	
	public String getNombre() {
		return nombrePersona;
	}
	
	public String getEmail() {
		return emailPersona;
	}
	
	public String getMensaje() {
		return mensajePersona;
	}

}
