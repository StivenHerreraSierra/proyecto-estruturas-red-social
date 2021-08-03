package co.edu.estructuras.red.model.exception;

import java.io.Serializable;

/**
 * @author Stiven Herrera Sierra
 */
public class ChatException extends Exception implements Serializable {
    public ChatException(String msj) {
        super(msj);
    }
}
