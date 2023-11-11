package br.com.ihm.davilnv.exception;

import java.io.Serial;

public class ViewException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ViewException(String message) {
        super(message);
    }
}
