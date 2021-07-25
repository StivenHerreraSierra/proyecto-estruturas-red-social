package estructuras.exception;

/**
 * @author Stiven Herrera Sierra.
 * Excepción relacionada con las operaciones sobre un nodo.
*/
public class NodoException extends Exception {
	private static final long serialVersionUID = 1L;

	public NodoException(String msj) {
		super(msj);
	}
}
