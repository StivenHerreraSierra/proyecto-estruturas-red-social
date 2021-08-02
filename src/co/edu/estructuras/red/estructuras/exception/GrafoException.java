package co.edu.estructuras.red.estructuras.exception;

import java.io.Serializable;

/**
 * @author Stiven Herrera Sierra.
 * Excepción relacionada con las operaciones sobre un grafo dirigido.
*/
public class GrafoException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public GrafoException(String msj) {
		super(msj);
	}
}
