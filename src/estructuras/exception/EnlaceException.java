package estructuras.exception;

/**
 * @author Stiven Herrera Sierra.
 * Excepción relacionada con las operaciones sobre un enlace.
*/
public class EnlaceException extends Exception {
	private static final long serialVersionUID = 1L;

	public EnlaceException(String msj) {
		super(msj);
	}
}
