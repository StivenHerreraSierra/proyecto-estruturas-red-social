package co.edu.estructuras.red.model.exception;

import java.io.Serializable;

/**
 * @author Stiven Herrera Sierra
 */
public class PublicacionException extends Exception implements Serializable {
    public PublicacionException(String msj) {
        super(msj);
    }
}
