package co.edu.estructuras.red.model.exception;

import java.io.Serializable;

/**
 * @author Stiven Herrera Sierra
 */
public class VendedorException extends Exception implements Serializable {
    public VendedorException(String msj) {
        super(msj);
    }
}
