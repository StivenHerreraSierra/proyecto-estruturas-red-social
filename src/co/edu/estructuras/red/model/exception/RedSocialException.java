package co.edu.estructuras.red.model.exception;

import java.io.Serializable;

public class RedSocialException extends Exception implements Serializable {
    public RedSocialException(String msj) {
        super(msj);
    }
}
