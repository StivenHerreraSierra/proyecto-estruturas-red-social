package co.edu.estructuras.red.estructuras.exception;

import java.io.Serializable;

/**
 * @author Stiven Herrera Sierra.
 * Excepción relacionada con las operaciones sobre un nodo.
*/
public class NodoException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public NodoException(String msj) {
		super(msj);
	}
}
