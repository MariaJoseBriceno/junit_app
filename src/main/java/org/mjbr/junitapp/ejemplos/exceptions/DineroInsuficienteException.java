package org.mjbr.junitapp.ejemplos.exceptions;

public class DineroInsuficienteException extends RuntimeException {

    public DineroInsuficienteException(String message) {
        super(message);
    }
}
