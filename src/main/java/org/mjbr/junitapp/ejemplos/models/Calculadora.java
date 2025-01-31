package org.mjbr.junitapp.ejemplos.models;

import org.mjbr.junitapp.ejemplos.exceptions.DineroInsuficienteException;
import org.mjbr.junitapp.ejemplos.exceptions.DivisionPorCeroException;

public class Calculadora {

    public double dividir(int a, int b) {
        if (b == 0 ) {
            throw new DivisionPorCeroException("Division por cero");
        }
        return (double) a/b;
    }
}
