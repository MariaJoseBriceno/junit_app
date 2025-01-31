package org.mjbr.junitapp.ejemplos.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mjbr.junitapp.ejemplos.exceptions.DineroInsuficienteException;
import org.mjbr.junitapp.ejemplos.exceptions.DivisionPorCeroException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculadoraTest {

    @Test
    @DisplayName("Prueba de division con rango para datos tipo double")
    void testDivision() {
         Calculadora calc = new Calculadora();
         double resultado = calc.dividir(10, 3);
         double esperado = 3.333;
         assertEquals(esperado, resultado, 0.001, "La división debe estar en el rango de 3.332 y 3.334");
    }


    @Test
    @DisplayName("Excepcion cuando hay división por cero")
    void testDivisioPorCeroException() {
        Calculadora calc = new Calculadora();

        Exception exception = assertThrows(DivisionPorCeroException.class, () -> {
            calc.dividir(10,0);
        });
        String actual = exception.getMessage();
        String esperado = "Division por cero";
        assertEquals(esperado, actual);

    }

}
