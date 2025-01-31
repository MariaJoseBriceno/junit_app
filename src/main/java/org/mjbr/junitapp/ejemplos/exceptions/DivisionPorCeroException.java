package org.mjbr.junitapp.ejemplos.exceptions;

public class DivisionPorCeroException extends RuntimeException{

    public DivisionPorCeroException(String message) {
        super(message);
    }
}
