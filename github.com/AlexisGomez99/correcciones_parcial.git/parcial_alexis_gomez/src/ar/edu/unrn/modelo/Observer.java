package ar.edu.unrn.modelo;

public interface Observer {

	void enviarEmail(String email, String asunto, String textoEmail);

}
