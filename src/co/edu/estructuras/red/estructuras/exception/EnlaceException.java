package co.edu.estructuras.red.estructuras.exception;

import java.io.Serializable;

/**
 * @author Stiven Herrera Sierra.
 * Excepción relacionada con las operaciones sobre un enlace.
*/
public class EnlaceException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public EnlaceException(String msj) {
		super(msj);
	}
}
